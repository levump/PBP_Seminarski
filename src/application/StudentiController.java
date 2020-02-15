package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import objects.Student;
//Novi importi
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;

public class StudentiController extends ConnectionFrame {
	public StudentiController(){
		
	}
	
	//DEKLARACIJE
	@FXML private javafx.scene.control.Button backToBaseFormButton;
	@FXML private javafx.scene.control.TableColumn<Student, String> Ime;
	@FXML private javafx.scene.control.TableColumn<Student, String> Prezime;
	@FXML private javafx.scene.control.TableColumn<Student, String> Indeks;
	@FXML private javafx.scene.text.Text statusText;
	@FXML private javafx.scene.control.TableView<Student> resultTable;
	
	//Initialize se automatski pokrece pri otvaranju forme, ovde stavljati stvari koje se automatski upisuju u elemente forme
	@FXML
	public void initialize() {
			
		Ime.setCellValueFactory(new PropertyValueFactory<Student, String>("Ime"));
		Prezime.setCellValueFactory(new PropertyValueFactory<Student, String>("Prezime"));
		Indeks.setCellValueFactory(new PropertyValueFactory<Student, String>("Indeks"));
		
		ObservableList<Student> listaStudenata = FXCollections.observableArrayList(bh.getStudents(smer, ispitni_rok, predmet));
		resultTable.setItems(listaStudenata);

		
//POCETAK EDITA - Promenjeno je da radi na dupli click

		
		resultTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
				resultTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
						    	try {
						    		String indeks = newSelection.getIndeks();
						    		System.out.println("handler index: "+ indeks);
						    		selectedStudent = bh.getStudentDetails(predmet, ispitni_rok, indeks);
						    		System.out.println("selectedStudent: " +selectedStudent.toString());
						    		
						            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/DetaljiStudenta.fxml"));
						                    Parent root1 = (Parent) fxmlLoader.load();
						                    Stage stage = new Stage();
						                    stage.setScene(new Scene(root1,600,400));  
						                    stage.setTitle("Detalji izabranog studenta");
						                    stage.show();
						            } catch(Exception e) {
						               e.printStackTrace();
						              }
				            }
				        }
				    }
				});
				

		        
		    }
		});

		
//KRAJ EDITA		
	}
	
	@FXML
	public void backToBaseForm(){
		
		resetFrameParameters();
		Stage stage1 = (Stage) backToBaseFormButton.getScene().getWindow();
		stage1.close();
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/BaseForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1,800,600));  
                stage.setTitle("Unosenje rezultata ispita");
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
	}
}
