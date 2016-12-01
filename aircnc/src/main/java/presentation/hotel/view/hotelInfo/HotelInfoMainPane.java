package presentation.hotel.view.hotelInfo;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.view.HotelMainController;
import presentation.hotel.view.hotelInfo.fxml.HotelInfoMainController;

public class HotelInfoMainPane {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private HotelInfoMainController controller;
	
	public HotelInfoMainPane(Stage primaryStage){
		this.primaryStage = primaryStage;
		initRootLayout();
	}

	public void initRootLayout() {
        try {
        	// Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/HotelInfoMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (HotelInfoMainController)loader.getController();
			rootLayout = loader.getRoot();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public HotelInfoMainController getController(){
		return controller;
	}

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public BorderPane getBorderPane() {
        return rootLayout;
    }
}
