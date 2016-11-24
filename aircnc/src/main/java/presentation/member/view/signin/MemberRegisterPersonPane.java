package presentation.member.view.signin;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.signin.fxml.MemberRegisterPersonController;
import presentation.member.view.signin.fxml.MemberSignInController;

/**
 * the pane of register (Personal)
 * @author ParanoiaSun
 *
 */

public class MemberRegisterPersonPane{
	
	private Pane registerPersonLayout;
	private MemberRegisterPersonController controller;


	public MemberRegisterPersonPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberRegisterPerson.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());

            loader.load();
            controller = (MemberRegisterPersonController)loader.getController();
            registerPersonLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberRegisterPersonController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return registerPersonLayout;
    }
	

}
