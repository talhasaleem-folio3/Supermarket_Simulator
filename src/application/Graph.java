package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graph {

	private static Stage graphStage;

	public static void displayGraphScene() {

		graphStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("Graph.fxml"));
			Scene scene = new Scene(root);
			graphStage.setTitle("Customer Summary");
			graphStage.setScene(scene);
			graphStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getGraphStage() {
		return graphStage;
	}

}
