package presentation.hotel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.hotel.accessor.HotelLoginAccessor;
import presentation.hotel.accessor.impl.HotelLoginAccessorImpl;
import presentation.hotel.view.signIn.HotelSignInPane;

/**
 * use to control sign in
 * only control the small stage
 * @author jqwu
 *
 */
public class CenterController extends Application{
	private Stage primaryStage;
	private Scene scene;
	
	private HotelSignInPane signIn;
	
	private final static int Login_Width = 550;
	private final static int Login_Height = 385;
	
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		
		// show the pane of sign in.
		signIn = new HotelSignInPane();
		scene = new Scene(signIn.getBorderPane(), Login_Width, Login_Height);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		signIn.getController().setCenterController(this);
		
	}
	
	public void initializeClient() {
		primaryStage.close();
		HotelCenterController client = new HotelCenterController();
		try {
			client.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeWindow(){
		this.primaryStage.close();
	}

}
