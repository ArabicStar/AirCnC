package presentation.market.view.unexecuteOrder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.unexecuteOrder.fxml.UnexecuteOrderController;

public class UnexecuteOrderPane {
	private Pane orderLayout;
	private UnexecuteOrderController controller;
	
	public UnexecuteOrderPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/UnexecuteOrder.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (UnexecuteOrderController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UnexecuteOrderController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
