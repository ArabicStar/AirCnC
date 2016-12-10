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
			System.out.println(2);
			loader.load();
			System.out.println(3);
			controller = (CreditTopUpController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			System.out.println("出错了");
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
