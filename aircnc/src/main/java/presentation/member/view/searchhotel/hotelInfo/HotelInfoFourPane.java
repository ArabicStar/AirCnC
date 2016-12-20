package presentation.member.view.searchhotel.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.searchhotel.hotelInfo.fxml.HotelInfoThreeController;

public class HotelInfoFourPane {
	private Pane rootLayout;
	private HotelInfoThreeController controller;


	public HotelInfoFourPane(){
		init();
	}

	public void init() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelInfoThree.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelInfoThreeController)loader.getController();
            rootLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelInfoThreeController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return rootLayout;
    }
}
