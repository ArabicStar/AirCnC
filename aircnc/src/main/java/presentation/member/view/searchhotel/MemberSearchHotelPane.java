package presentation.member.view.searchhotel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import presentation.member.view.searchhotel.fxml.MemberSearchHotelController;

public class MemberSearchHotelPane {
	
	private AnchorPane searchLayout;
	private MemberSearchHotelController controller;


	public MemberSearchHotelPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberSearchHotel.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberSearchHotelController)loader.getController();
            searchLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberSearchHotelController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return searchLayout;
    }
}
