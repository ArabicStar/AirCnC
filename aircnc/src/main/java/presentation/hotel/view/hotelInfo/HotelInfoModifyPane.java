package presentation.hotel.view.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.hotel.view.hotelInfo.fxml.HotelInfoModifyController;

public class HotelInfoModifyPane {
	private Pane infoLayout;
	private HotelInfoModifyController controller;


	public HotelInfoModifyPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelInfoModify.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelInfoModifyController)loader.getController();
			infoLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelInfoModifyController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return infoLayout;
    }
}
