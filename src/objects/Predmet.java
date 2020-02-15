package objects;

import java.util.ArrayList;

public class Predmet {

	private String naziv;
	private String id_predmeta;
	
	public Predmet(){
		
	}
	
	public Predmet(String naziv, String id_predmeta){
		this.naziv = naziv;
		this.id_predmeta = id_predmeta;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getId_predmeta() {
		return id_predmeta;
	}
	public void setId_predmeta(String id_predmeta) {
		this.id_predmeta = id_predmeta;
	}
	
	public ArrayList<String> getAllNaziv(ArrayList<Predmet> predmetList){
		ArrayList<String> res = new ArrayList<>();
		for(Predmet p: predmetList){
			res.add(p.getNaziv());
		}
		return res;
	}

	
}
