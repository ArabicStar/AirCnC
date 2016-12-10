package presentation.market.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.myorder.fxml.MyOrderController;

public class MyOrderPane {
	private Pane orderLayout;
	private MyOrderController controller;
	
	public MyOrderPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/MyOrder.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (MyOrderController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MyOrderController getController() {
		return this.controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
