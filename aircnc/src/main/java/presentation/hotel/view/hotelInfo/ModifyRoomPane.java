package presentation.hotel.view.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import presentation.hotel.view.hotelInfo.fxml.ModifyRoomController;


public class ModifyRoomPane {
	private Pane orderLayout;
	private ModifyRoomController controller;


	public ModifyRoomPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/ModifyRoom.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (ModifyRoomController)loader.getController();
            orderLayout = loader.getRoot();
            
            DropShadow ds = new DropShadow();
            
            ds.setOffsetY(5.0);
            ds.setOffsetX(5.0);
            ds.setColor(Color.GRAY);
            
            orderLayout.setEffect(ds);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ModifyRoomController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
