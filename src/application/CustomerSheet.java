package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerSheet {

	private static Stage customerSheetStage;

	public static void displayCustomerSheetScene() {

		customerSheetStage = new Stage();
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("CustomerSheet.fxml"));
			Scene scene = new Scene(root);
			customerSheetStage.setTitle("Customer Summary");
			customerSheetStage.setScene(scene);
			customerSheetStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getCustomerSheetStage() {
		return customerSheetStage;
	}

}
