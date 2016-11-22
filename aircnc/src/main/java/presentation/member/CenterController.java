package presentation.member;

import presentation.member.view.MemberStartPane;
import presentation.member.view.fxml.MemberSignInController;
import presentation.member.view.MemberSignInPane;
import presentation.member.view.MemberRegisterMainPane;
import presentation.member.view.MemberRegisterPersonPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.MemberRegisterEnterprisePane;

/**
 * the whole stage.
 * @author paranoia
 *
 */

public class CenterController extends Application{
	
	private Stage primaryStage;
	private MemberStartPane start;
	private MemberSignInPane signIn;
	private MemberRegisterMainPane registerMain;
	private MemberRegisterPersonPane registerPerson;
	private MemberRegisterEnterprisePane registerBusiness;
	private BorderPane rootLayout;
	
	private Scene scene;
	
	private MemberSignInController signInController;
	
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
		this.primaryStage=primaryStage;
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
        primaryStage.setResizable(false);
        
        //show the pane of sign in.
		start = new MemberStartPane(primaryStage);
		rootLayout = start.getBorderPane();
        scene = new Scene(start.getBorderPane(),Login_Width,Login_Height);
        primaryStage.setScene(scene);
        
        addSignInPane();
        
        primaryStage.show();
	}
	
	public void addSignInPane(){
		start.getBorderPane().getChildren().clear();
		signIn = new MemberSignInPane();
		start.getBorderPane().setCenter(signIn.getPane());
		signIn.getController().setCenterController(this);
	}
	
	public void addRegisterPane(){
		start.getBorderPane().getChildren().clear();
		registerMain = new MemberRegisterMainPane();
		registerMain.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerMain.getPane());
	}
	
	public void addRegisterPersonPane(){
		start.getBorderPane().getChildren().clear();
		registerMain = new MemberRegisterMainPane();
		registerMain.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerMain.getPane());
	}
	
	public void addRegisterBusinessPane(){
		start.getBorderPane().getChildren().clear();
		registerPerson = new MemberRegisterPersonPane();
		registerPerson.getController().setCenterController(this);
		start.getBorderPane().setCenter(registerPerson.getPane());
	}

}
