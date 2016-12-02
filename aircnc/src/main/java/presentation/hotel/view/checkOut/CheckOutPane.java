package presentation.hotel.view.checkOut;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.hotel.view.checkOut.fxml.CheckOutController;

public class CheckOutPane {
	private Pane orderLayout;
	private CheckOutController controller;


	public CheckOutPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/CheckOut.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (CheckOutController)loader.getController();
            orderLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CheckOutController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }	
}
