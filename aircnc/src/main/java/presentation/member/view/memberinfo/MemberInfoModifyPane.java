package presentation.member.view.memberinfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import presentation.member.view.memberinfo.fxml.MemberInfoModifyController;

public class MemberInfoModifyPane {
	
	private AnchorPane infoLayout;
	private MemberInfoModifyController controller;


	public MemberInfoModifyPane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberInfoModify.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberInfoModifyController)loader.getController();
			infoLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberInfoModifyController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return infoLayout;
    }
    
}
