package presentation.market.view.searchhotel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.market.view.searchhotel.fxml.SearchHotelController;

public class SearchHotelPane {
	private Pane orderLayout;
	private SearchHotelController controller;
	
	public SearchHotelPane() {
		init();
	}
	
	public void init() {
		try {
			// Load sign in overview.
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource("fxml/SearchHotel.fxml");
			loader.setLocation(location);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			loader.load();
			controller = (SearchHotelController) loader.getController();
			orderLayout = loader.getRoot();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public SearchHotelController getController() {
		return controller;
	}
	
	public Pane getPane() {
		return orderLayout;
	}
}
