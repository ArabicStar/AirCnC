package presentation.manage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * the whole stage. contains the connection of every pane
 * 
 * @author paranoia
 *
 */

public class CenterController extends Application {

	private Stage primaryStage;
	
	private Scene scene;

	private final static int Main_Width = 1024;
	private final static int Main_Height = 768;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	/**
	 * initialize the stage
	 * 
	 * @author paranoia
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		//start = new MemberStartPane(primaryStage);
		//scene = new Scene(start.getBorderPane(), Login_Width, Login_Height);
		primaryStage.setScene(scene);

		addSignInPane();

		primaryStage.show();
	}

	/**
	 * add the pane of sign in (MemberSignInPane)
	 */
	public void addSignInPane() {
		
	}

	

}
