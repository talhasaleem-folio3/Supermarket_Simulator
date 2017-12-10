package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.PromptMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PromptMessageController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Label message;
	
	@FXML
	private void closeButton(){
		PromptMessage.getPromptMessageStage().close();
	}

}
