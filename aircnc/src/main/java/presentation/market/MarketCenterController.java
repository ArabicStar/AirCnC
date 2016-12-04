package presentation.market;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MarketCenterController extends Application {
	private Stage primaryStage;
	private Scene scene;
	private BorderPane rootLayout;
	
	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);
		
		primaryStage.setScene(new Scene(new BorderPane(), Client_Width, Client_Height));
		
		primaryStage.show();
	}

}
