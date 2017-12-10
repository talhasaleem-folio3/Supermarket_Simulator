package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.FinalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class FinalWindowController implements Initializable{

    @FXML
    private Button finishButton;

    @FXML
    void finishButton(ActionEvent event) {
    	FinalWindow.getFinalWindowStage().close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
