package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulatorData {

	private static Stage simulatorDataStage;
	
	public static void displaySimulatorDataScene() {

		simulatorDataStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("SimulatorData.fxml"));
			Scene scene = new Scene(root);
			simulatorDataStage.setTitle("Simulator Data");
			simulatorDataStage.setScene(scene);
			simulatorDataStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getSimulatorDataStage(){
		return simulatorDataStage;
	}
	
}
