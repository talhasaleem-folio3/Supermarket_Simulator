package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExpressGraph {

	private static Stage expressGraphStage;

	public static void displayExpressGraphScene() {

		expressGraphStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("ExpressGraph.fxml"));
			Scene scene = new Scene(root);
			expressGraphStage.setTitle("Express Graph");
			expressGraphStage.setScene(scene);
			expressGraphStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getExpressGraphStage() {
		return expressGraphStage;
	}

}
