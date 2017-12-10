package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import application.AlertBox;
import application.CustomerSheet;
import application.Graph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerSheetController implements Initializable {

	// ------> Instance Variables <------

	private int regularCurrent;
	private int expressCurrent;

	private double times = 0;
	private int qnum = 0;

	public static ArrayList<Integer> regularQueue;
	public static ArrayList<Integer> regularDuplicateQueue;
	public static ArrayList<Integer> expressQueue;
	public static ArrayList<Integer> expressDuplicateQueue;

	public static ArrayList<Integer> regularServer;
	public static ArrayList<Integer> expressServer;

	public static ArrayList<DisplaySheet> customerSheet;

	public static ArrayList<DisplaySheet> regularCustomerSheet;
	public static ArrayList<DisplaySheet> expressCustomerSheet;

	public static ArrayList<Double> regularSum;
	public static ArrayList<Double> expressSum;
	
	public static ArrayList<Double> regularUtilSum;
	public static ArrayList<Double> expressUtilSum;

	// ------> Constructor <------

	public CustomerSheetController() {

		this.regularCurrent = -1;
		this.expressCurrent = -1;

		regularQueue = new ArrayList<>();
		regularDuplicateQueue = new ArrayList<>();
		expressQueue = new ArrayList<>();
		expressDuplicateQueue = new ArrayList<>();

		regularServer = new ArrayList<>();
		expressServer = new ArrayList<>();

		Collections.sort(customerSheet);

		regularCustomerSheet = new ArrayList<>();
		expressCustomerSheet = new ArrayList<>();

		fillCustomerSheets();

		Collections.sort(regularCustomerSheet);
		Collections.sort(expressCustomerSheet);

		regularSum = new ArrayList<>();
		expressSum = new ArrayList<>();
		
		regularUtilSum = new ArrayList<>();
		expressUtilSum = new ArrayList<>();

		fillQueues();
		makeZero();

		timeAvg();
		utilAvg();

	}

	// ------> FXML IDs <------

	@FXML
	private Button nextButton;

	@FXML
	private Button exitButton;

	@FXML
	private TableView<DisplaySheet> customerTable;

	@FXML
	private TableColumn<DisplaySheet, Integer> customerTag;

	@FXML
	private TableColumn<DisplaySheet, Double> time;

	@FXML
	private TableColumn<DisplaySheet, String> event;

	@FXML
	private TableColumn<DisplaySheet, String> customerType;

	// ------> FXML Methods <------

	@FXML
	private void nextButton() {
		Graph.displayGraphScene();
		CustomerSheet.getCustomerSheetStage().close();
	}

	@FXML
	private void exitButton() {
		AlertBox.displayAlertBoxScene(CustomerSheet.getCustomerSheetStage());
	}

	// ------> Methods <------

	// Overriding Initialize Method

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeColumns();
		populateTable();
	}

	// Initialize Columns

	@FXML
	private void initializeColumns() {
		customerTag.setCellValueFactory(new PropertyValueFactory<>("customerTag"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		event.setCellValueFactory(new PropertyValueFactory<>("event"));
		customerType.setCellValueFactory(new PropertyValueFactory<>("customerType"));
	}

	private void populateTable() {
		for (int i = 0; i < customerSheet.size(); i++) {
			customerTable.getItems().add((customerSheet.get(i)));
		}
	}

	private void fillQueues() {

		for (int i = 0; i < regularCustomerSheet.size(); i++) {

			if (customerSheet.get(i).getEvent().equals("Arrival")) {
				regularCurrent++;
				regularQueue.add(regularCurrent);
				regularServer.add(1);
			} else {
				regularCurrent--;
				if (regularCurrent < 0) {
					regularCurrent = -1;
					regularServer.add(0);
				} else {
					regularServer.add(1);
				}
				regularQueue.add(regularCurrent);

			}
		}

		for (int i = 0; i < expressCustomerSheet.size(); i++) {
			if (customerSheet.get(i).getEvent().equals("Arrival")) {
				expressCurrent++;
				expressQueue.add(expressCurrent);
				expressServer.add(1);
			} else {
				expressCurrent--;
				if (expressCurrent < 0) {
					expressCurrent = -1;
					expressServer.add(0);
				} else {
					expressServer.add(1);
				}
				expressQueue.add(expressCurrent);
			}
		}
	}

	private void fillCustomerSheets() {
		for (int i = 0; i < customerSheet.size(); i++) {
			if (customerSheet.get(i).getCustomerType().equals("Regular Customer")) {
				regularCustomerSheet.add(customerSheet.get(i));
			} else {
				expressCustomerSheet.add(customerSheet.get(i));
			}
		}
	}

	private void makeZero() {
		for (int i = 0; i < regularQueue.size(); i++) {
			if (regularQueue.get(i) < 0) {
				regularDuplicateQueue.add(0);
			} else {
				regularDuplicateQueue.add(regularQueue.get(i));
			}
		}

		for (int i = 0; i < expressQueue.size(); i++) {
			if (expressQueue.get(i) < 0) {
				expressDuplicateQueue.add(0);
			} else {
				expressDuplicateQueue.add(expressQueue.get(i));
			}
		}
	}

	private void timeAvg() {

		qnum = regularDuplicateQueue.get(0);
		times = regularCustomerSheet.get(0).getTime();
		for (int i = 0; i < regularDuplicateQueue.size(); i++) {
			if (regularDuplicateQueue.get(i) != qnum) {
				regularSum.add((regularCustomerSheet.get(i).getTime() - times) * qnum);
				times = regularCustomerSheet.get(i).getTime();
				qnum = regularDuplicateQueue.get(i);
			}
		}

		qnum = expressDuplicateQueue.get(0);
		times = expressCustomerSheet.get(0).getTime();
		for (int i = 0; i < expressDuplicateQueue.size(); i++) {
			if (expressDuplicateQueue.get(i) != qnum) {
				expressSum.add((expressCustomerSheet.get(i).getTime() - times) * qnum);
				times = expressCustomerSheet.get(i).getTime();
				qnum = expressDuplicateQueue.get(i);
			}
		}
	}
	
	private void utilAvg() {

		qnum = regularServer.get(0);
		times = regularCustomerSheet.get(0).getTime();
		for (int i = 0; i < regularServer.size(); i++) {
			if (regularServer.get(i) != qnum) {
				regularUtilSum.add((regularCustomerSheet.get(i).getTime() - times) * qnum);
				times = regularCustomerSheet.get(i).getTime();
				qnum = regularServer.get(i);
			}
		}

		qnum = expressServer.get(0);
		times = expressCustomerSheet.get(0).getTime();
		for (int i = 0; i < expressServer.size(); i++) {
			if (expressServer.get(i) != qnum) {
				expressUtilSum.add((expressCustomerSheet.get(i).getTime() - times) * qnum);
				times = expressCustomerSheet.get(i).getTime();
				qnum = expressServer.get(i);
			}
		}
	}

}
