package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage startupStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			startupStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Supermarket Simulator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getStartupStage() {
		return startupStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
