package presentation.member.view.signin;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.signin.fxml.MemberSignInController;

/**
 * the pane of sign in(General)
 * @author ParanoiaSun
 *
 */

public class MemberSignInPane {

	private Pane signInLayout;
	private MemberSignInController controller;


	public MemberSignInPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberSignIn.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberSignInController)loader.getController();
			signInLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberSignInController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return signInLayout;
    }
	
	

}
