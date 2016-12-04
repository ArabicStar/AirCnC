package presentation.market.view.signin;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Water
 *
 */
public class MarketSignInPane {
	
	private Pane signInLayout;
	private MarketSignInController controller;
	private BorderPane rootLayout;
	
	public MarketSignInPane() {
		init();
	}
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MarketSignIn.fxml"));
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            rootLayout = (BorderPane) loader.load();
            controller = (MarketSignInController)loader.getController();
			signInLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public MarketSignInController getController() {
		return controller;
	}

    public Node getPane(){
    	return signInLayout;
    }
    
    public BorderPane getBorderPane() {
    	if(rootLayout == null) {
    		System.out.println("Null BorderPane is returned; some mistakes may happen in Unicode.");
    	}
        return rootLayout;
    }
	
	
}
