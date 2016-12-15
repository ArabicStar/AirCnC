package presentation.member.view.searchhotel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import presentation.member.view.searchhotel.fxml.SupremeSearchController;

/**
 * the pane of hotel search
 * @author paranoia
 *
 */
public class SupremeSearchPane {
	
	private AnchorPane searchLayout;
	private SupremeSearchController controller;


	public SupremeSearchPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/SupremeSearch.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (SupremeSearchController)loader.getController();
            searchLayout = loader.getRoot();
            
            DropShadow ds = new DropShadow();
            
            ds.setOffsetY(5.0);
            ds.setOffsetX(5.0);
            ds.setColor(Color.GRAY);
            
            searchLayout.setEffect(ds);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SupremeSearchController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return searchLayout;
    }
    
}
