package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.Main;
import application.SimulatorData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StartupController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button continueButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private void continueButton(){
		SimulatorData.displaySimulatorDataScene();
		Main.getStartupStage().close();
	}
	
	@FXML
	private void exitButton(){
		AlertBox.displayAlertBoxScene(Main.getStartupStage());
	}

}
