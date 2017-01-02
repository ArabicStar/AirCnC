package presentation.member.view.browsehotel.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.searchhotel.hotelInfo.fxml.HotelInfoOneController;

public class BrowseInfoOnePane {
	private Pane orderLayout;
	private HotelInfoOneController controller;


	public BrowseInfoOnePane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelInfoOne.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelInfoOneController)loader.getController();
            orderLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelInfoOneController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
