package presentation.member.view.browsehotel.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.searchhotel.hotelInfo.fxml.HotelHistoryOrderController;

public class BrowseHistoryOrdersPane {
	private Pane orderLayout;
	private HotelHistoryOrderController controller;


	public BrowseHistoryOrdersPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelHistoryORder.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = loader.getController();
            orderLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelHistoryOrderController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
