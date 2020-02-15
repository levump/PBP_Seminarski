package seminarski.dataController;

import java.util.ArrayList;

import objects.Predmet;
import objects.Smer;
import objects.Student;
import objects.StudentDetails;

import java.sql.*;


public class BackgroundHandler {

	
	private Connection conn;
	private ArrayList<Predmet> predmetList;
	private ArrayList<String> IRList;
	private ArrayList<Student> sudentsList;
	static{
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public BackgroundHandler(){
		connectToDB2();
	}
	
	//ZATVARANJE KONEKCIJE SA BAZOM
	public void closeConnection(){
		try {
			this.conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			System.out.println("SQL error: SQLCODE" + e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	//KREIRANJE KONEKCIJE SA BAZOM
	private void connectToDB2(){

		String url = "jdbc:db2://localhost:50000/VSTUD";	
		try{
			this.conn = DriverManager.getConnection(url,"Alek","tresnjica5");
			System.out.println("Connected");
		} catch (SQLException e){
			System.out.println("SQL error: SQLCODE " + e.getErrorCode());
		} catch (Exception e) {
			System.out.println("Greska prilikom povezivanja na bazu");
			e.printStackTrace();
		}
	}
	
	//METODA ZA ISPIS SMEROVA
	public ArrayList<Smer> getSmerovi(){
		String query = getStaticSmerQuery();
		ArrayList<Smer> list = new ArrayList<>();
		list.clear();
		try{
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				list.add(new Smer(resultSet.getString(1), resultSet.getString(2)));
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage()+ "\n*************Greska u metodi getSmer************");
			e.printStackTrace();
		}
		if(list.isEmpty()){
			list.add(new Smer());
		}
		
		return list;
	}


	//METODA ZA ISPIS ROKOVA
	public ArrayList<String> getRokovi(){
		String query = getIspitniRokQuery();
		IRList = new ArrayList<>();
		IRList.clear();
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				IRList.add(resultSet.getString(1));
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage()+ "\n*************Greska u metodi getRokovi************");
			e.printStackTrace();
		}
		if(IRList.isEmpty()){
			IRList.add("Lista ispitnih rokova je prazna");
		}
		return IRList;
	}


	//METODA ZA ISPIS PREDMETA
	public ArrayList<Predmet> getPredmeti(String ispitni_rok, String smer){
		String query = getPredmetQuery(ispitni_rok, smer);
		predmetList = new ArrayList<>();
		predmetList.clear();
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				predmetList.add(new Predmet(resultSet.getString(1), resultSet.getString(2)));
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage()+ "\n*************Greska u metodi getPredmeti************");
			e.printStackTrace();
		}
		if(predmetList.isEmpty()){
			predmetList.add(new Predmet("Lista predmeta je prazna", ""));
		}
		return predmetList;
	}
	
	
	public ArrayList<Student> getStudents(String smer, String ispitni_rok, String predmet){
		String query = getStudentsQuery(smer, ispitni_rok, predmet);
		sudentsList = new ArrayList<>();
		sudentsList.clear();
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				sudentsList.add(new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(sudentsList.isEmpty()){
			sudentsList.add(new Student());
		}
		return sudentsList;
		
	}
	
	
	public StudentDetails getStudentDetails(String id_predmeta, String ispitni_rok, String indeks){
		String query = getStudentDetailsQuery(id_predmeta, ispitni_rok, indeks);
		StudentDetails studentDetailsResult = new StudentDetails();
		ResultSet resultSet;
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				studentDetailsResult = new StudentDetails(resultSet.getInt(1),
														  resultSet.getString(2),
														  resultSet.getInt(3), 
														  resultSet.getString(4), 
														  resultSet.getInt(5),
														  resultSet.getString(6),
														  resultSet.getString(7),
														  resultSet.getString(8),
														  resultSet.getInt(9),
														  resultSet.getString(10),
														  resultSet.getString(11),
														  resultSet.getString(12),
														  resultSet.getString(13),
														  resultSet.getString(14),
														  resultSet.getInt(15),
														  resultSet.getInt(16),
														  resultSet.getString(17),
														  resultSet.getString(18));
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return studentDetailsResult;
	}
	
	
	public boolean updateStudent(StudentDetails sd){
		String query = getUpdateStudentQuery(sd);
		try{
			PreparedStatement preparedStatement = this.conn.prepareStatement(query);
			int resultNum = preparedStatement.executeUpdate();
			if(resultNum > 0){
				return true;
			}
		} catch (SQLException | NullPointerException e){
			System.out.println(e.getMessage()+ "\n*************Greska u metodi getSmer************");
			e.printStackTrace();
		}
		return false;
	}
	
	
	private String getStaticSmerQuery(){
		return "SELECT id_smera, naziv FROM smer ORDER BY naziv, id_smera";		
	}
	
	
	private String getIspitniRokQuery(){
		return "SELECT naziv FROM ispitni_rok ORDER BY godina, oznaka";		
	}
	
	
	private String getPredmetQuery(String ispitni_rok, String smer){
		return "SELECT p.naziv, p.id_predmeta FROM ispitni_rok ir JOIN ispit i ON ir.oznaka = i.oznaka_roka AND ir.godina = i.godina_roka " +
		"JOIN predmet p ON p.id_predmeta = i.id_predmeta JOIN obavezan_predmet op ON op.id_predmeta = p.id_predmeta JOIN smer s "+
		"ON s.id_smera = op.id_smera WHERE ir.naziv = '" + ispitni_rok + "' AND s.id_smera = '" + smer + "' GROUP BY p.naziv, p.id_predmeta";
	}
	
	
	private String getStudentsQuery(String smer, String ispitni_rok, String predmet){
		return "select ime, prezime, indeks "+
				"from dosije dx join smer sx on dx.id_smera=sx.id_smera "+
				"join obavezan_predmet opx on opx.id_smera=sx.id_smera "+
				"join predmet px on px.id_predmeta=opx.id_predmeta "+
				"where sx.id_smera='" + smer + "' and px.id_predmeta='" + predmet + "' "+
				"and indeks in (select indeks from ispit ix join ispitni_rok irx "+
 					"on ix.godina_roka=irx.godina and ix.oznaka_roka=irx.oznaka "+
 					"where naziv='" + ispitni_rok + "' AND ix.id_predmeta = " + predmet +") ORDER BY dx.indeks";
	}
	
	
	private String getStudentDetailsQuery(String id_predmeta, String ispitni_rok, String indeks){
		return "SELECT "
				+ " i.indeks, i.id_predmeta, i.godina_roka, i.oznaka_roka,"
				+ " i.godina, i.semestar, i.datum_prijave, i.nacin_prijave, i.broj_polaganja, i.status_prijave,"
				+ " i.datum_pismenog, i.bodovi_pismenog, i.datum_usmenog, i.bodovi_usmenog,"
				+ " i.bodovi, i.ocena, i.nastavnik, i.napomena"
				+ " FROM ispit i join ispitni_rok ir ON ir.godina = i.godina_roka AND ir.oznaka = i.oznaka_roka"
				+ " WHERE ir.naziv = '" + ispitni_rok + "' AND i.indeks =" + indeks 
				+ " AND i.id_predmeta = " + id_predmeta + "";
	}


	private String getUpdateStudentQuery(StudentDetails sd){
		return "UPDADE ispit i SET i.indeks = " + sd.getIndeks() + ", i.id_predmeta, i.godina_roka, i.oznaka_roka,"
				+ " i.godina, i.semestar, i.datum_prijave, i.nacin_prijave, i.broj_polaganja, i.status_prijave,"
				+ " i.datum_pismenog, i.bodovi_pismenog, i.datum_usmenog, i.bodovi_usmenog,"
				+ " i.bodovi, i.ocena, i.nastavnik, i.napomena)"
				+ " WHERE i.indeks = "+ sd.getIndeks() + " AND i.id_predmeta = " + sd.getId_predmeta() + " "
						+ "AND "+ sd.getOznaka_roka() + sd.getGodina_roka()
						+ " IN (SELECT ir.naziv FROM ispitni_rok ir"
						+ " WHERE ir.oznaka = i.oznaka_roka AND ir.godina = i.godina_roka)";
	}
}
