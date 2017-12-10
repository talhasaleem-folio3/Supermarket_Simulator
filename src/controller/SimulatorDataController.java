package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.PromptMessage;
import application.SimulatorData;
import application.TableDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SimulatorDataController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		choiceBoxMethod();
		getFields();
	}
	
	static double regularInterarrival;
	static double expressInterarrival;
	static double regularService;
	static double expressService;
	static String time;
	
	public SimulatorDataController() {
		
		regularInterarrival = 0.0;
		expressInterarrival = 0.0;
		regularService = 0.0;
		expressService = 0.0;
		time = null;
	}
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private ChoiceBox<String> distributionChoiceBox;
	
	@FXML
	private TextField regularInterarrivalField;
	
	@FXML
	private TextField expressInterarrivalField;
	
	@FXML
	private TextField regularServiceField;
	
	@FXML
	private TextField expressServiceField;
	
	@FXML
	private TextField serviceTimeField;

	@FXML
	private void okButton(){
		if(getFields()){
			TableDisplay.displayTableDisplayScene();
			SimulatorData.getSimulatorDataStage().close();
		} else {
			PromptMessage.displayPromptMessageScene();
		}
	}
	
	@FXML
	private void cancelButton(){
		AlertBox.displayAlertBoxScene(SimulatorData.getSimulatorDataStage());
	}
	
	private void choiceBoxMethod(){
		distributionChoiceBox.getItems().add("Exponential");
		distributionChoiceBox.setValue("Exponential");
	}
	
	private boolean getFields(){
		
		try {
			regularInterarrival = Double.parseDouble(regularInterarrivalField.getText());
			expressInterarrival = Double.parseDouble(expressInterarrivalField.getText());
			regularService = Double.parseDouble(regularServiceField.getText());
			expressService = Double.parseDouble(expressServiceField.getText());
			time = serviceTimeField.getText();
			
			String min = time.substring(0, 2);
			String sec = time.substring(time.length() - 2, time.length());
			
			int a = Integer.parseInt(min);
			int b = Integer.parseInt(sec);
			
			String ms = min + ":" + sec;
			String ms2 = a + ":" + b;
			
			if(!time.equals(ms) && !time.equals(ms2))
				return false;
			
			return true;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
	}
	
}
