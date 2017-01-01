package presentation.market;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.market.view.signin.MarketSignInPane;

/**
 * use to control the jump of different scene
 * @author Water
 *
 */
public class CenterController extends Application{
	private Stage primaryStage;
	private Scene scene;	
	
	private MarketSignInPane signIn;
	
	private final static int Login_Width = 550;
	private final static int Login_Height = 385;
	

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);
		
		// show the pane of sign in
		signIn = new MarketSignInPane();
		scene = new Scene(signIn.getBorderPane(), Login_Width, Login_Height);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		signIn.getController().setCenterController(this);
	}
	
	public void initializeClient() {
		primaryStage.close();
		// change to the new view of market
		MarketCenterController client = new MarketCenterController();
		try {
			client.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeWindow(){
		this.primaryStage.close();
	}

}
