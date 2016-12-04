package presentation.market.view;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MarketMainPane {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MarketMainController controller;
	
	
	public MarketMainPane() {
		
	}
	
	public MarketMainPane(Stage primaryStage) {
		this.primaryStage = primaryStage;
		initRootLayout();
		
	}
	
	public void initRootLayout() {
		try {
        	// Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("MarketMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MarketMainController)loader.getController();
			rootLayout = loader.getRoot();

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

	public MarketMainController getController() {
		return controller;
	}
}
