package presentation.member.view.browsehotel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.browsehotel.fxml.BrowseHotelsController;

public class BrowseHotelsPane {
	
	private Pane orderLayout;
	private BrowseHotelsController controller;

	public BrowseHotelsPane(){
		init();
	}
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/BrowseHotels.fxml");
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

    public BrowseHotelsController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
