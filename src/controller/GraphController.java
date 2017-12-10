package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.AlertBox;
import application.ExpressGraph;
import application.Graph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class GraphController implements Initializable {

	@FXML
	private Button nextButton;

	@FXML
	private Button exitButton;

	@FXML
	private BarChart<String, Number> regularQueue;

	@FXML
	private BarChart<String, Number> regularServer;

	@FXML
	private void nextButton() {
		ExpressGraph.displayExpressGraphScene();
		Graph.getGraphStage().close();
	}

	@FXML
	private void exitButton() {
		AlertBox.displayAlertBoxScene(Graph.getGraphStage());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeBarChart();
	}

	private void initializeBarChart() {
		XYChart.Series<String, Number> regularQueueBarChart = new XYChart.Series<String, Number>();

		for (int i = 0; i < CustomerSheetController.regularCustomerSheet.size(); i++) {

			double value = CustomerSheetController.regularQueue.get(i);

			if (value < 0) {
				value = 0;
			}

			regularQueueBarChart.getData().add(new XYChart.Data<String, Number>(
					"" + CustomerSheetController.regularCustomerSheet.get(i).getTime(), value));
		}

		regularQueue.getData().add(regularQueueBarChart);

		XYChart.Series<String, Number> regularServerBarChart = new XYChart.Series<String, Number>();

		for (int i = 0; i < CustomerSheetController.regularCustomerSheet.size(); i++) {

			double value = CustomerSheetController.regularServer.get(i);

			if (value < 0) {
				value = 0;
			}

			regularServerBarChart.getData().add(new XYChart.Data<String, Number>(
				"" + CustomerSheetController.regularCustomerSheet.get(i).getTime(), value));
		}

		regularServer.getData().add(regularServerBarChart);

	}

}
