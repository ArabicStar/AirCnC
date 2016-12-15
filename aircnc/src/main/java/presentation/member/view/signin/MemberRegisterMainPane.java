package presentation.member.view.signin;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;
import presentation.member.view.signin.fxml.MemberRegisterMainController;
import javafx.scene.Node;

/**
 * the pane of register(Main)
 * @author ParanoiaSun
 *
 */

public class MemberRegisterMainPane {

	private Pane registerLayout;
	private MemberRegisterMainController controller;
	
	public MemberRegisterMainPane(){
		init();
	}
	
	public void init() {
        try {
			
			FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberRegisterMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberRegisterMainController)loader.getController();
			registerLayout = loader.getRoot();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public MemberRegisterMainController getController(){
		return controller;
	}
	
	public Node getPane(){
		return registerLayout;
	}
}
