package presentation.hotel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.hotel.view.HotelMainPane;

public class HotelCenterController extends Application{
	private Stage primaryStage;
	private Scene scene;
	private BorderPane rootLayout;
	
	private HotelMainPane mainClient;
	
	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		mainClient = new HotelMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getBorderPane();
		scene = new Scene(mainClient.getBorderPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);

		// addSignInPane();

		primaryStage.show();
		
	}
}
