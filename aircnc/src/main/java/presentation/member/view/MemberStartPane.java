package presentation.member.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * the pane of sign in(scene)
 * @author paranoia
 *
 */

public class MemberStartPane{

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public MemberStartPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}

	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MemberStartPane.class.getResource("fxml/MemberStart.fxml"));
            rootLayout = (BorderPane) loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public BorderPane getBorderPane() {
        return rootLayout;
    }


}
