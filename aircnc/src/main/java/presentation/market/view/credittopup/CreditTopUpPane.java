package presentation.market.view.credittopup;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.credittopup.fxml.CreditChargeController;

public class CreditTopUpPane {
	private Pane orderLayout;
	private CreditChargeController controller;
	
	public CreditTopUpPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/CreditCharge.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (CreditChargeController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CreditChargeController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
