package application;


import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objects.Predmet;
import objects.Smer;

public class BaseFormController extends ConnectionFrame {
	
	//static BackgroundHandler bh = new BackgroundHandler(); //uspostavljanje konekcije sa bazom
	
	public BaseFormController() {
	}
	
	//DEKLARACIJE
	@FXML private javafx.scene.control.Button buttonExit1;	
	@FXML private ChoiceBox<String> mainDrop1;
	@FXML private ChoiceBox<String> mainDrop2;
	@FXML private ChoiceBox<String> mainDrop3;
	@FXML private TextArea errorField1;
	@FXML private javafx.scene.control.Button bSubmit;
	
	
	//Initialize se automatski pokrece pri otvaranju forme, 
	//ovde stavljati stvari koje se automatski upisuju u elemente forme
	@FXML
	public void initialize() {		//OBRATI PAZNJU DA OVA KLASA NASLEDJUJE KLASU CONNECTIONFRAME 
				//KOJA INICIJALIZUJE KONEKCIJU KA BAZI I SADRZI GLOBALNE PROMELJIVE KOJE CE DA SE PROSLEDJUJU DALJE
		
		try{
			mainDrop1.setTooltip(new Tooltip("Izaberite smer"));
			mainDrop2.setTooltip(new Tooltip("Izaberite ispitni rok"));
			mainDrop3.setTooltip(new Tooltip("Izaberite predmet"));

			final ArrayList<String> listaRokova = new ArrayList<>(bh.getRokovi());	//poziv metode za rokove
			final ArrayList<Smer> listaSmerova = new ArrayList<>(bh.getSmerovi());
			
			ArrayList<String> idSmera = new ArrayList<>();
			ArrayList<String> naziviSmerova = new ArrayList<>();
			
			ArrayList<Predmet> listaObjPredmeta = new ArrayList<>();
			ObservableList<String> listaPredmeta = FXCollections.observableArrayList();
			
			for(Smer s: listaSmerova){
				idSmera.add(s.getId());
				naziviSmerova.add(s.getNaziv().trim()+ " ID_" + s.getId());
			}
				
			mainDrop1.setItems(FXCollections.observableArrayList(naziviSmerova));	//Ubacivanje listi smerova i rokova u drop liste
			mainDrop2.setItems(FXCollections.observableArrayList(listaRokova));
			mainDrop3.setItems(listaPredmeta);
			
			
			mainDrop1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<?extends Number> vl, Number old_value, Number new_value){
					smer = listaSmerova.get(new_value.intValue()).getId();
					System.out.println("smer: "  +smer);
					listaObjPredmeta.clear();
					listaPredmeta.clear();
					resetPredmet();
					if(smer.length()>0 && ispitni_rok.length()>1){
						listaObjPredmeta.addAll(bh.getPredmeti(ispitni_rok, smer));
						for(Predmet p: listaObjPredmeta){
							listaPredmeta.add(p.getNaziv().trim());
						}
					}
				}
			});
			
			
			mainDrop2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<?extends Number> vl, Number old_value, Number new_value){
					ispitni_rok = listaRokova.get(new_value.intValue());
					System.out.println("ispitni rok: " + ispitni_rok);
					listaObjPredmeta.clear();
					listaPredmeta.clear();
					resetPredmet();
					if(smer.length()>0 && ispitni_rok.length()>1){
						listaObjPredmeta.addAll(bh.getPredmeti(ispitni_rok, smer));
						for(Predmet p: listaObjPredmeta){
							listaPredmeta.add(p.getNaziv().trim());
						}

					}					
				}
			});
			
			mainDrop3.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<?extends Number> vl, Number old_value, Number new_value){
					if(!listaPredmeta.isEmpty()){
						predmet = listaObjPredmeta.get(new_value.intValue()).getId_predmeta();
						System.out.println("id_predmeta: "+ predmet);
					} else {
						resetPredmet();
					}
				}
			});
			
		} catch (Exception e) {
			System.out.println("**********************ERROR**************************");
			errorField1.setText(e.getMessage());
			e.getMessage();			
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void onMainExit(){
		Stage stage = (Stage) buttonExit1.getScene().getWindow();
		bh.closeConnection();	// PRI ZATVARANJU APP GASIMO KONEKCIJE KA BAZI
		stage.close();
	}
	
	
	public void actionSubmit(ActionEvent event) throws Exception { //PROMENA
		if(smer.length()==0 || ispitni_rok.length()<2 || predmet.length()<2 || smer.contains("prazna") || ispitni_rok.contains("prazna") || predmet.contains("prazna")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Pogresan unos");
			alert.setHeaderText("Neko od polja nije pravilno izabrano");
			alert.setContentText("Molimo vas da izaberete sva polja"); //+ smer +": " +  smer.length() + " " + ispitni_rok.length() + " " + predmet.length());

			alert.showAndWait();
		} else {
			Stage stage1 = (Stage) buttonExit1.getScene().getWindow();
			stage1.close();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Studenti.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1,800,600));  
				stage.setTitle("Lista studenata");
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
