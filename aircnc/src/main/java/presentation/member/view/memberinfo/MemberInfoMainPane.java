package presentation.member.view.memberinfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.memberinfo.fxml.MemberInfoMainController;

/**
 * this is the pane of member info(main)
 * @author paranoia
 *
 */
public class MemberInfoMainPane {
	
	private Pane infoLayout;
	private MemberInfoMainController controller;


	public MemberInfoMainPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberInfoMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberInfoMainController)loader.getController();
			infoLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberInfoMainController getController(){
    	return this.controller;
    }
    
    public Node getContentPane(){
    	return infoLayout;
    }
}
