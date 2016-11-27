package presentation.hotel.view.signIn;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HotelSignInPane {
	private Pane signInLayout;
	private HotelSignInController controller;


	public HotelSignInPane(){
		init();
	}
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelSignIn.fxml"));
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelSignInController)loader.getController();
			signInLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public HotelSignInController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return signInLayout;
    }
}
