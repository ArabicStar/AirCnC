package presentation.member.view;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.member.view.memberinfo.fxml.MemberMainController;

public class MemberMainPane {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MemberMainController controller;
	
	public MemberMainPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}

	public void initRootLayout() {
        try {
        	// Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("memberinfo/fxml/MemberMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberMainController)loader.getController();
			rootLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public MemberMainController getController(){
		return controller;
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public AnchorPane getAnchorPane() {
        return rootLayout;
    }

}
