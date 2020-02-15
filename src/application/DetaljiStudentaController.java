package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DetaljiStudentaController extends ConnectionFrame{
	public DetaljiStudentaController(){
		
	}
	//DEKLARACIJE
	@FXML javafx.scene.control.Button closeInfo;
	@FXML javafx.scene.control.TextField indeks;
	@FXML javafx.scene.control.TextField id_predmeta;
	@FXML javafx.scene.control.TextField godina_roka;
	@FXML javafx.scene.control.TextField oznaka_roka;
	@FXML javafx.scene.control.TextField godina;
	@FXML javafx.scene.control.TextField semestar;
	@FXML javafx.scene.control.TextField datum_prijave;
	@FXML javafx.scene.control.TextField nacin_prijave;
	@FXML javafx.scene.control.TextField broj_polaganja;
	@FXML javafx.scene.control.TextField status_prijave;
	@FXML javafx.scene.control.TextField datum_pismenog;
	@FXML javafx.scene.control.TextField bodovi_pismenog;
	@FXML javafx.scene.control.TextField datum_usmenog;
	@FXML javafx.scene.control.TextField bodovi_usmenog;
	@FXML javafx.scene.control.TextField bodovi;
	@FXML javafx.scene.control.TextField ocena;
	@FXML javafx.scene.control.TextField nastavnik;
	@FXML javafx.scene.control.TextArea napomena;
	
	//Initialize se automatski pokrece pri otvaranju forme, ovde stavljati stvari koje se automatski upisuju u elemente forme
	@FXML
	public void initialize() {
		
		System.out.println("selectedStudent in studDet: " +selectedStudent.toString());
		
		indeks.setText(selectedStudent.getStringIndeks());
		id_predmeta.setText(selectedStudent.getId_predmeta());
		godina_roka.setText(selectedStudent.getStringGodina_roka());
		oznaka_roka.setText(selectedStudent.getOznaka_roka());
		godina.setText(selectedStudent.getStringGodina());
		semestar.setText(selectedStudent.getSemestar());
		datum_prijave.setText(selectedStudent.getDatum_prijave());
		nacin_prijave.setText(selectedStudent.getNacin_prijave());
		broj_polaganja.setText(selectedStudent.getStringBroj_polaganja());
		status_prijave.setText(selectedStudent.getStatus_prijave());
		datum_pismenog.setText(selectedStudent.getDatum_pismenog());
		bodovi_pismenog.setText(selectedStudent.getBodovi_pismenog());
		datum_usmenog.setText(selectedStudent.getDatum_usmenog());
		bodovi_usmenog.setText(selectedStudent.getBodovi_usmenog());
		bodovi.setText(selectedStudent.getStringBodovi());
		ocena.setText(selectedStudent.getStringOcena());
		nastavnik.setText(selectedStudent.getNastavnik());
		napomena.setText(selectedStudent.getNapomena());	
		
	}	

	
	@FXML
	public void closeStudent(){
		Stage stage1 = (Stage) closeInfo.getScene().getWindow();
		stage1.close();
	}
	@FXML
	public void upisiUBazu(){
		//Upisi promene u bazu
		
//		ERROR ZA NEUSPELU TRANSAKCIJU, PREMESTITI U PROVERU {TODO}
//		Alert alert = new Alert(AlertType.ERROR);
//		alert.initStyle(StageStyle.UTILITY);
//		alert.setTitle("Transaction failed");
//		alert.setHeaderText("Transakcija je neuspesna");
//		alert.setContentText("Error code: "); // DODATI + ERRCODE, eventualno proveru za popunjenost polja? {TODO}
//
//		alert.showAndWait();
	}

}
