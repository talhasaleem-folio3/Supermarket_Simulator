package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.ExpressGraph;
import application.Report;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ExpressGraphController implements Initializable {

	@FXML
	private Button nextButton;

	@FXML
	private Button exitButton;

	@FXML
	private BarChart<String, Number> expressQueue;

	@FXML
	private BarChart<String, Number> expressServer;

	@FXML
	private void nextButton() {
		Report.displayReportScene();
		ExpressGraph.getExpressGraphStage().close();
	}

	@FXML
	private void exitButton() {
		AlertBox.displayAlertBoxScene(ExpressGraph.getExpressGraphStage());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeBarChart();

	}

	private void initializeBarChart() {
		XYChart.Series<String, Number> expressQueueBarChart = new XYChart.Series<String, Number>();

		for (int i = 0; i < CustomerSheetController.expressCustomerSheet.size(); i++) {

			double value = CustomerSheetController.expressQueue.get(i);

			if (value < 0) {
				value = 0;
			}

			expressQueueBarChart.getData().add(new XYChart.Data<String, Number>(
					"" + CustomerSheetController.expressCustomerSheet.get(i).getTime(), value));
		}

		expressQueue.getData().add(expressQueueBarChart);

		XYChart.Series<String, Number> expressServerBarChart = new XYChart.Series<String, Number>();

		for (int i = 0; i < CustomerSheetController.expressCustomerSheet.size(); i++) {

			double value = CustomerSheetController.expressServer.get(i);

			if (value < 0) {
				value = 0;
			}

			expressServerBarChart.getData().add(new XYChart.Data<String, Number>(
					"" + CustomerSheetController.expressCustomerSheet.get(i).getTime(), value));
		}

		expressServer.getData().add(expressServerBarChart);

	}

}
