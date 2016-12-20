package presentation.manage.view.hotelmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.manage.view.hotelmanage.fxml.HotelInfoOneController;

public class HotelInfoOnePane {
	private Pane orderLayout;
	private HotelInfoOneController controller;


	public HotelInfoOnePane(){
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
            controller = loader.getController();
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
