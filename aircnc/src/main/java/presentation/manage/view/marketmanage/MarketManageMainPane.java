package presentation.manage.view.marketmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.member.view.memberinfo.fxml.MemberMainController;

public class MarketManageMainPane {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MemberMainController controller;
	
	public MarketManageMainPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}
	
	/**
	 * Load market manage overview.
	 */
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MarketManageMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
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
