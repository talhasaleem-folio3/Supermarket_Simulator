package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PromptMessage {
	
	private static Stage promptMessageStage;
	
	public static void displayPromptMessageScene() {

		promptMessageStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("PromptMessage.fxml"));
			Scene scene = new Scene(root);
			promptMessageStage.initModality(Modality.APPLICATION_MODAL);
			promptMessageStage.setTitle("Error!");
			promptMessageStage.setScene(scene);
			promptMessageStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPromptMessageStage(){
		return promptMessageStage;
	}


}
