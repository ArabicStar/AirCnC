package presentation.member;

import presentation.member.view.signin.MemberStartPane;
import presentation.member.view.signin.fxml.MemberSignInController;
import presentation.member.view.signin.MemberSignInPane;
import presentation.member.view.signin.MemberRegisterMainPane;
import presentation.member.view.signin.MemberRegisterPersonPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.MemberMainPane;
import presentation.member.view.signin.MemberRegisterEnterprisePane;

/**
 * the whole stage. contains the connection of every pane of sign in and
 * register.
 * 
 * @author paranoia
 *
 */

public class CenterController extends Application {

	private Stage primaryStage;

	private MemberStartPane start;
	private MemberSignInPane signIn;
	private MemberRegisterMainPane registerMain;
	private MemberRegisterPersonPane registerPerson;
	private MemberRegisterEnterprisePane registerBusiness;

	private MemberMainPane mainclient;

	private BorderPane rootLayout;

	private Scene scene;

	private final static int Login_Width = 550;
	private final static int Login_Height = 385;

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
		start = new MemberStartPane(primaryStage);
		rootLayout = start.getBorderPane();
		scene = new Scene(start.getBorderPane(), Login_Width, Login_Height);
		primaryStage.setScene(scene);

		addSignInPane();

		primaryStage.show();
	}

	/**
	 * add the pane of sign in (MemberSignInPane)
	 */
	public void addSignInPane() {
		start.getBorderPane().getChildren().clear();
		signIn = new MemberSignInPane();
		start.getBorderPane().setCenter(signIn.getPane());
		signIn.getController().setCenterController(this);
	}

	/**
	 * add the pane of register (MemberRegisterMainPane)
	 */
	public void addRegisterPane() {
		start.getBorderPane().getChildren().clear();
		registerMain = new MemberRegisterMainPane();
		registerMain.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerMain.getPane());
	}

	/**
	 * add the pane of personal register (MemberRegisterPersonPane)
	 */
	public void addRegisterPersonPane() {
		start.getBorderPane().getChildren().clear();
		registerPerson = new MemberRegisterPersonPane();
		registerPerson.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerPerson.getPane());
	}

	/**
	 * add the pane of business register (MemberRegisterEnterprisePane)
	 */
	public void addRegisterBusinessPane() {
		start.getBorderPane().getChildren().clear();
		registerBusiness = new MemberRegisterEnterprisePane();
		registerBusiness.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerBusiness.getPane());
	}

	/**
	 * jump to the main client
	 */
	public void initializeClient() {
		primaryStage.close();
		ClientCenterController client = new ClientCenterController();
		try {
			client.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
