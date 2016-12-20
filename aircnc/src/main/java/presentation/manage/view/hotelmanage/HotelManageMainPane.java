package presentation.manage.view.hotelmanage;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.manage.view.hotelmanage.fxml.HotelManageMainController;

public class HotelManageMainPane {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private HotelManageMainController controller;
	
	public HotelManageMainPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}
	
	/**
	 * Load hotel manage overview.
	 */
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelManageMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = loader.getController();
			rootLayout = loader.getRoot();
			controller.setRootLayout(rootLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public HotelManageMainController getController(){
		return controller;
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public AnchorPane getAnchorPane() {
        return rootLayout;
    }
}
