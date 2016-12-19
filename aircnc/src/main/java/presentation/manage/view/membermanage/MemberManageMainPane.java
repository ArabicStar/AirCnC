package presentation.manage.view.membermanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.manage.view.membermanage.fxml.MemberManageMainController;

/**
 * the main pane of the whole client.
 * @author paranoia
 *
 */
public class MemberManageMainPane {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MemberManageMainController controller;
	
	public MemberManageMainPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}
	
	/**
	 * Load member manage overview.
	 */
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            //这里不对
            URL location = getClass().getResource("fxml/MemberManageMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = loader.getController();
			rootLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public MemberManageMainController getController(){
		return controller;
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public AnchorPane getAnchorPane() {
        return rootLayout;
    }
}
