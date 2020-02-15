package application;

import objects.StudentDetails;
import seminarski.dataController.BackgroundHandler;

public class ConnectionFrame {

	static BackgroundHandler bh = new BackgroundHandler(); //uspostavljanje konekcije sa bazom
	static String ispitni_rok = "";
	static String smer = "";
	static String predmet = "";
	static StudentDetails selectedStudent = new StudentDetails();
	
	public void resetFrameParameters(){
		ispitni_rok = "";
		smer = "";
		predmet = "";
	}
	
	public void resetPredmet(){
		predmet = "";
	}
	
}
