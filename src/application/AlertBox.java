package application;

import controller.AlertBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	private static Stage alertBoxStage;
	
	public static void displayAlertBoxScene(Stage stageInstance) {

		alertBoxStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("AlertBox.fxml"));
			AlertBoxController.setClosingStage(stageInstance);
			Scene scene = new Scene(root);
			alertBoxStage.initModality(Modality.APPLICATION_MODAL);
			alertBoxStage.setTitle("Exit Window");
			alertBoxStage.setScene(scene);
			alertBoxStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getAlertBoxStage(){
		return alertBoxStage;
	}


}
