package presentation.member;

import presentation.member.view.signin.MemberStartPane;
import presentation.member.view.signin.MemberSignInPane;
import presentation.member.view.signin.MemberRegisterMainPane;
import presentation.member.view.signin.MemberRegisterPersonPane;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.accessor.MemberLoginAccessor;
import presentation.member.accessor.RegisterEnterpriseAccessor;
import presentation.member.accessor.RegisterPersonAccessor;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.accessor.impl.RegisterEnterpriseAccessorImpl;
import presentation.member.accessor.impl.RegisterPersonAccessorImpl;
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
	
	private MemberLoginAccessor loginAccessor;
	private RegisterPersonAccessor registerPersonAccessor;
	private RegisterEnterpriseAccessor registerEnterpriseAccessor;
	
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
		scene = new Scene(start.getBorderPane(), Login_Width, Login_Height);
		primaryStage.setScene(scene);

		addSignInPane();

		primaryStage.show();
	}

	/**
	 * add the pane of sign in (MemberSignInPane)
	 */
	public void addSignInPane() {
		if(!MemberLoginAccessorImpl.isLaunched())
			MemberLoginAccessorImpl.launch();
		URL location = getClass().getResource("/images/member/register/login_BG.png");
		start.getBorderPane().setStyle("-fx-background-image: url("+location+");");
		loginAccessor = new MemberLoginAccessorImpl();
		start.getBorderPane().getChildren().clear();
		signIn = new MemberSignInPane();
		start.getBorderPane().setCenter(signIn.getPane());
		signIn.getController().setCenterController(this);
		signIn.getController().setAccessor(loginAccessor);
	}

	/**
	 * add the pane of register (MemberRegisterMainPane)
	 */
	public void addRegisterPane() {
		if(!RegisterAccessorImpl.isLaunched())
			RegisterAccessorImpl.launch();
		URL location = getClass().getResource("../../images/member/register/register_BG.png");
		start.getBorderPane().setStyle("-fx-background-image: url("+location+");");
		registerPersonAccessor = new RegisterPersonAccessorImpl();
		registerEnterpriseAccessor = new RegisterEnterpriseAccessorImpl();
		start.getBorderPane().getChildren().clear();
		registerMain = new MemberRegisterMainPane();
		registerMain.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerMain.getPane());
		registerMain.getController().setPersonAccessor(registerPersonAccessor);
		registerMain.getController().setEnterPriseAccessor(registerEnterpriseAccessor);
	}

	/**
	 * add the pane of personal register (MemberRegisterPersonPane)
	 */
	public void addRegisterPersonPane() {
		start.getBorderPane().getChildren().clear();
		registerPerson = new MemberRegisterPersonPane();
		registerPerson.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerPerson.getPane());
		registerPerson.getController().setAccessor(registerPersonAccessor);
	}

	/**
	 * add the pane of business register (MemberRegisterEnterprisePane)
	 */
	public void addRegisterBusinessPane() {
		start.getBorderPane().getChildren().clear();
		registerBusiness = new MemberRegisterEnterprisePane();
		registerBusiness.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerBusiness.getPane());
		registerBusiness.getController().setAccessor(registerEnterpriseAccessor);
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
