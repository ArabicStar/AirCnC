package presentation.member.view;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.fxml.MemberRegisterEnterpriseController;

/**
 * the pane of register (Business)
 * @author ParanoiaSun
 *
 */

public class MemberRegisterEnterprisePane{

	private Pane registerBusinessLayout;
	private MemberRegisterEnterpriseController controller;


	public MemberRegisterEnterprisePane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberRegisterEnterprise.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());

            loader.load();
            controller = (MemberRegisterEnterpriseController)loader.getController();
            registerBusinessLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	/**
	 * return the controller of this pane.
	 * @return MemberRegisterEnterpriseController
	 */
    public MemberRegisterEnterpriseController getController(){
    	return this.controller;
    }
    
    
    /**
     * return the pane
     * @return Node(MemberRegisterEnterprisePane)
     */
    public Node getPane(){
    	return registerBusinessLayout;
    }
}
