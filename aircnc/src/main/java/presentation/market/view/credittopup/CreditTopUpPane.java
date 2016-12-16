package presentation.market.view.credittopup;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.credittopup.fxml.CreditTopUpController;

public class CreditTopUpPane {
	private Pane orderLayout;
	private CreditTopUpController controller;
	
	public CreditTopUpPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/CreditTopUp.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (CreditTopUpController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CreditTopUpController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
