package presentation.member.view.creditchange;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.creditchange.fxml.MemberCreditChangeController;

public class MemberCreditChangePane {
	
	private Pane creditLayout;
	private MemberCreditChangeController controller;


	public MemberCreditChangePane(){
		init();
	}

	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberCreditChange.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberCreditChangeController)loader.getController();
			creditLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberCreditChangeController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return creditLayout;
    }
}
