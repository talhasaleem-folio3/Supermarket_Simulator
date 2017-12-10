package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.AlertBox;
import application.CustomerSheet;
import application.SimulatorData;
import application.TableDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Talha Saleem
 *
 *         Add Queue. Generate all Four Bar Chart graphs. Generate Result.
 *
 */

public class TableDisplayController implements Initializable {

	// ------> Instance Variables <------

	// Input Data Variables

	private static double regularMeanInterarrival;
	private static double expressMeanInterarrival;
	private static double regularMeanService;
	private static double expressMeanService;
	private static String time;

	// For Time Breaking

	private static int min;
	private static double sec;

	// Common For Both Systems (Regular & Express)

	private static int customerNumber;
	private static double totalTime;
	private static ArrayList<DisplaySheet> customerSheet;

	// For Regular System

	private double timeSum;
	private static double regularDelay;
	
	private static ArrayList<Double> regularArrival;
	private static ArrayList<Double> regularDeparture;

	// For Express System

	private double expressTimeSum;
	private static double expressDelay;

	private static ArrayList<Double> expressArrival;
	private static ArrayList<Double> expressDeparture;

	// Desired Instances

	private Random rand;
	private DecimalFormat df;
	private Thread thread1;
	private Thread thread2;

	// ------> Constructor <------

	public TableDisplayController() {

		// Input Data Variables

		regularMeanInterarrival = SimulatorDataController.regularInterarrival;
		expressMeanInterarrival = SimulatorDataController.expressInterarrival;
		regularMeanService = SimulatorDataController.regularService;
		expressMeanService = SimulatorDataController.expressService;
		time = SimulatorDataController.time;

		// For Time Breaking

		min = 0;
		sec = 0.0;

		// Common For Both Systems (Regular & Express)

		customerNumber = 1;
		totalTime = 0.0;
		customerSheet = new ArrayList<>();

		// For Regular System

		this.timeSum = 0.0;
		regularDelay = 0.0;

		regularArrival = new ArrayList<>();
		regularDeparture = new ArrayList<>();

		// For Express System

		this.expressTimeSum = 0.0;
		expressDelay = 0.0;

		expressArrival = new ArrayList<>();
		expressDeparture = new ArrayList<>();

		// Desired Instances

		this.rand = new Random();
		this.df = new DecimalFormat("#.00");

		initializeThreads();

		// Checking Time Correct Format (08:00 should be 8:00)

		String temp = time.substring(0, 2);

		if (temp.charAt(0) != '0') {
			min = Integer.parseInt(temp);
		}

		else {
			min = Integer.parseInt(time.substring(1, 2));
		}

		sec = Double.parseDouble(time.substring(time.length() - 2, time.length()));

		totalTime = min + (sec / 60.0);

	}

	// ------> FXML IDs <------

	@FXML
	private Label timer;

	@FXML
	private Button backButton;

	@FXML
	private Button nextButton;

	@FXML
	private Button exitButton;

	@FXML
	private TableView<RegularSystem> regularTable;

	@FXML
	private TableColumn<RegularSystem, Integer> regCustomerTagColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regInterarrivalTimeColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regServiceTimeColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regAbsoluteArrivalColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regDepartureColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regDelayColumn;

	@FXML
	private TableColumn<RegularSystem, Double> regWaitingTimeColumn;

	@FXML
	private TableView<ExpressSystem> expressTable;

	@FXML
	private TableColumn<ExpressSystem, Integer> expCustomerTagColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expInterarrivalTimeColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expServiceTimeColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expAbsoluteArrivalColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expDepartureColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expDelayColumn;

	@FXML
	private TableColumn<ExpressSystem, Double> expWaitingTimeColumn;

	// ------> FXML Methods <------

	@FXML
	private void backButton() {
		SimulatorData.displaySimulatorDataScene();
		TableDisplay.getTableDisplayStage().close();
	}

	@FXML
	private void nextButton() {
		CustomerSheetController.customerSheet = customerSheet;
		CustomerSheet.displayCustomerSheetScene();
		TableDisplay.getTableDisplayStage().close();
	}

	@FXML
	private void exitButton() {
		AlertBox.displayAlertBoxScene(TableDisplay.getTableDisplayStage());
	}

	// ------> Methods <------

	// Overriding Initialize Method

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		timer.setText(time);
		initializeColumns();
		thread1.start();
		thread2.start();
	}

	// Fill Regular Table

	private void populateRegularTable() {

		double previousAbsoluteArrival = 0;
		double previousDeparture = 0;
		while (timeSum < totalTime) {

			int CT = customerNumber;
			double RIAT = generateRandomRegularInterarrival();
			double RST = generateRandomRegularService();
			double AA = RIAT + previousAbsoluteArrival;
			AA = Double.parseDouble(this.df.format(AA));

			if (AA > totalTime) {
				break;
			}

			regularArrival.add(AA);

			customerSheet.add(new DisplaySheet(customerNumber, AA, "Arrival", "Regular Customer"));

			double DLY;

			if (timeSum != 0) {
				DLY = previousDeparture - AA;
				if (DLY < 0) {
					DLY = 0;
				}
			} else {
				DLY = 0;
			}

			DLY = Double.parseDouble(this.df.format(DLY));
			
			regularDelay += DLY;

			double D = AA + DLY + RST;
			D = Double.parseDouble(this.df.format(D));

			regularDeparture.add(D);

			customerSheet.add(new DisplaySheet(customerNumber, D, "Departure", "Regular Customer"));

			double W = D - AA;
			W = Double.parseDouble(this.df.format(W));

			RegularSystem regSys = new RegularSystem(CT, RIAT, RST, AA, D, DLY, W);

			previousAbsoluteArrival = AA;
			previousDeparture = D;
			timeSum = AA;
			customerNumber++;

			regularTable.getItems().add(regSys);
		}

	}

	// Fill Express Table

	private void populateExpressTable() {

		double previousAbsoluteArrival = 0;
		double previousDeparture = 0;
		while (expressTimeSum < totalTime) {

			int CT = customerNumber;
			double EIAT = generateRandomExpressInterarrival();
			double EST = generateRandomExpressService();
			double AA = EIAT + previousAbsoluteArrival;
			AA = Double.parseDouble(this.df.format(AA));

			if (AA > totalTime) {
				break;
			}

			expressArrival.add(AA);

			customerSheet.add(new DisplaySheet(customerNumber, AA, "Arrival", "Express Customer"));

			double DLY;

			if (expressTimeSum != 0) {
				DLY = previousDeparture - AA;
				if (DLY < 0) {
					DLY = 0;
				}
			} else {
				DLY = 0;
			}

			DLY = Double.parseDouble(this.df.format(DLY));
			
			expressDelay += DLY;

			double D = AA + DLY + EST;
			D = Double.parseDouble(this.df.format(D));

			expressDeparture.add(D);

			customerSheet.add(new DisplaySheet(customerNumber, D, "Departure", "Express Customer"));

			double W = D - AA;
			W = Double.parseDouble(this.df.format(W));

			ExpressSystem expSys = new ExpressSystem(CT, EIAT, EST, AA, D, DLY, W);

			previousAbsoluteArrival = AA;
			previousDeparture = D;
			expressTimeSum = AA;
			customerNumber++;

			expressTable.getItems().add(expSys);
		}

	}

	// Initialize both (Regular & Express) Table columns

	@FXML
	private void initializeColumns() {

		regCustomerTagColumn.setCellValueFactory(new PropertyValueFactory<>("customerTag"));
		regInterarrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("interarrivalTime"));
		regServiceTimeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceTime"));
		regAbsoluteArrivalColumn.setCellValueFactory(new PropertyValueFactory<>("absoluteArrival"));
		regDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
		regDelayColumn.setCellValueFactory(new PropertyValueFactory<>("delay"));
		regWaitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

		expCustomerTagColumn.setCellValueFactory(new PropertyValueFactory<>("customerTag"));
		expInterarrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("interarrivalTime"));
		expServiceTimeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceTime"));
		expAbsoluteArrivalColumn.setCellValueFactory(new PropertyValueFactory<>("absoluteArrival"));
		expDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
		expDelayColumn.setCellValueFactory(new PropertyValueFactory<>("delay"));
		expWaitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

	}

	// Random Number generation for Interarrival time of Regular System

	private double generateRandomRegularInterarrival() {
		return Double.parseDouble(df.format(Math.log(1 - rand.nextDouble()) * (-regularMeanInterarrival)));
	}

	// Random Number generation for Interarrival time of Express System

	private double generateRandomExpressInterarrival() {
		return Double.parseDouble(df.format(Math.log(1 - rand.nextDouble()) * (-expressMeanInterarrival)));
	}

	// Random Number generation for Service time of Regular System

	private double generateRandomRegularService() {
		return Double.parseDouble(df.format(Math.log(1 - rand.nextDouble()) * (-regularMeanService)));
	}

	// Random Number generation for Service time of Express System

	private double generateRandomExpressService() {
		return Double.parseDouble(df.format(Math.log(1 - rand.nextDouble()) * (-expressMeanService)));
	}

	// Initializes all the threads.

	private void initializeThreads() {

		thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				populateRegularTable();

				try {
					Thread.sleep(10000000);
				} catch (InterruptedException e) {
					System.out.println("Error in populateRegularTable() Thread");
				}
			}
		});

		thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				populateExpressTable();

				try {
					Thread.sleep(10000000);
				} catch (InterruptedException e) {
					System.out.println("Error in populateExpressTable() Thread");
				}
			}
		});

	}
	
	public static double getRegularDelay() {
		return Double.parseDouble(new DecimalFormat("#.000").format(regularDelay / regularArrival.size()));
	}
	
	public static double getExpressDelay() {
		return Double.parseDouble(new DecimalFormat("#.000").format(expressDelay / expressArrival.size()));
	}

}
