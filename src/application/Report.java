package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Report {
	
private static Stage reportStage;
	
	public static void displayReportScene() {

		reportStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("Report.fxml"));
			Scene scene = new Scene(root);
			reportStage.setTitle("Report");
			reportStage.setScene(scene);
			reportStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getReportStage(){
		return reportStage;
	}


}
