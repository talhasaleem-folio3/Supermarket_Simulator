package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertBoxController implements Initializable {
	
	private static Stage closingStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button yesButton;
	
	@FXML
	private Button noButton;
	
	@FXML
	private void yesButton(){
		AlertBox.getAlertBoxStage().close();
		closingStage.close();
	}
	
	@FXML
	private void noButton(){
		AlertBox.getAlertBoxStage().close();
	}
	
	public static void setClosingStage(Stage stage){
		closingStage = stage;
	}

}
