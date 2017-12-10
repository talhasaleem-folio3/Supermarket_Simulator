package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import application.AlertBox;
import application.FinalWindow;
import application.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReportController implements Initializable{

    @FXML
    private Label regAvgDelay;

    @FXML
    private Label regTimeAvg;

    @FXML
    private Label regUtilServer;

    @FXML
    private Label expAvgDelay;

    @FXML
    private Label expTimeAvg;

    @FXML
    private Label expUtilServer;

    @FXML
    private Button nextButton;

    @FXML
    private Button exitButton;

    @FXML
    void exitButton(ActionEvent event) {
    	AlertBox.displayAlertBoxScene(Report.getReportStage());
    }

    @FXML
    void nextButton(ActionEvent event) {
    	FinalWindow.displayFinalWindowScene();
    	Report.getReportStage().close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		regAvgDelay.setText("" + TableDisplayController.getRegularDelay() + " minutes");
		expAvgDelay.setText("" + TableDisplayController.getExpressDelay() + " minutes");
		
		
		
		regTimeAvg.setText("" + Double.parseDouble(new DecimalFormat("#.000").format(regTimeAvg())) + " minutes");
		expTimeAvg.setText("" + Double.parseDouble(new DecimalFormat("#.000").format(expTimeAvg())) + " minutes");
		
		
		
		regUtilServer.setText("" + ((1 - Double.parseDouble(new DecimalFormat("#.000").format(regUtilTimeAvg())))*100) + " %");
		expUtilServer.setText("" + ((1-Double.parseDouble(new DecimalFormat("#.000").format(expUtilTimeAvg())))*100) + " %");
	}
	
	private double regTimeAvg() {
		double sum = 0;
		
		for(int i = 0; i < CustomerSheetController.regularSum.size(); i++) {
			sum += CustomerSheetController.regularSum.get(i);
		}
		
		return sum / CustomerSheetController.regularCustomerSheet.get(CustomerSheetController.regularCustomerSheet.size()-2).getTime();
	}
	
	private double expTimeAvg() {
		double sum = 0;
		
		for(int i = 0; i < CustomerSheetController.expressSum.size(); i++) {
			sum += CustomerSheetController.expressSum.get(i);
		}
		
		return sum / CustomerSheetController.expressCustomerSheet.get(CustomerSheetController.expressCustomerSheet.size()-2).getTime();
	}
	
	private double regUtilTimeAvg() {
		double sum = 0;
		
		for(int i = 0; i < CustomerSheetController.regularUtilSum.size(); i++) {
			sum += CustomerSheetController.regularUtilSum.get(i);
		}
		
		return sum / CustomerSheetController.regularCustomerSheet.get(CustomerSheetController.regularCustomerSheet.size()-1).getTime();
	}
	
	private double expUtilTimeAvg() {
		double sum = 0;
		
		for(int i = 0; i < CustomerSheetController.expressUtilSum.size(); i++) {
			sum += CustomerSheetController.expressUtilSum.get(i);
		}
		
		return sum / CustomerSheetController.expressCustomerSheet.get(CustomerSheetController.expressCustomerSheet.size()-1).getTime();
	}

}
