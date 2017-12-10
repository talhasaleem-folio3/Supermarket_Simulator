package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableDisplay {
	
private static Stage tableDisplayStage;
	
	public static void displayTableDisplayScene() {

		tableDisplayStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("TableDisplay.fxml"));
			Scene scene = new Scene(root);
			tableDisplayStage.setTitle("Supermarket Queue");
			tableDisplayStage.setScene(scene);
			tableDisplayStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getTableDisplayStage(){
		return tableDisplayStage;
	}


}
