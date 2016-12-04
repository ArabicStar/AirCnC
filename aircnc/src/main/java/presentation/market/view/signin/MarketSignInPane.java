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
        	System.out.println(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MarketSignIn.fxml"));
            System.out.println(1);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            System.out.println(2);
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            rootLayout = (BorderPane) loader.load();
            System.out.println(3);
            controller = (MarketSignInController)loader.getController();
            System.out.println(4);
			signInLayout = loader.getRoot();
			System.out.println(5);

        } catch (IOException e) {
        	System.out.println("加载失败");
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
    		System.out.println("都是骗人的!");
    	}
        return rootLayout;
    }
	
	
}
