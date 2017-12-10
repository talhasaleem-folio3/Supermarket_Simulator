package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalWindow {
	
private static Stage finalWindowStage;
	
	public static void displayFinalWindowScene() {

		finalWindowStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("FinalWindow.fxml"));
			Scene scene = new Scene(root);
			finalWindowStage.setTitle("Special Thanks");
			finalWindowStage.setScene(scene);
			finalWindowStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getFinalWindowStage(){
		return finalWindowStage;
	}

}
