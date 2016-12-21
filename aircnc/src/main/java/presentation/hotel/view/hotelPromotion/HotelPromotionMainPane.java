package presentation.hotel.view.hotelPromotion;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.hotel.view.hotelPromotion.fxml.HotelPromotionMainController;

public class HotelPromotionMainPane {
	private Pane layout;
	private HotelPromotionMainController controller;


	public HotelPromotionMainPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelPromotionMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelPromotionMainController)loader.getController();
            layout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelPromotionMainController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return layout;
    }
}
