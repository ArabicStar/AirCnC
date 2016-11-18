package presentation.member;

import presentation.member.view.MemberStartPane;
import presentation.member.view.fxml.MemberSignInController;
import presentation.member.view.MemberSignInPane;
import presentation.member.view.MemberRegisterMainPane;
import presentation.member.view.MemberRegisterPersonPane;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.member.view.MemberRegisterEnterprisePane;

public class CenterController extends Application{
	
	private Stage primaryStage;
	private MemberStartPane start;
	private MemberSignInPane signIn;
	private MemberRegisterMainPane registerMain;
	private MemberRegisterPersonPane registerPerson;
	private MemberRegisterEnterprisePane registerBusiness;
	private BorderPane rootLayout;
	
	private MemberSignInController signInController;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		start = new MemberStartPane(primaryStage);
		rootLayout = start.getBorderPane();
		signIn = new MemberSignInPane(rootLayout,this);
	}
	
	public void addSignInPane(){
		rootLayout.getChildren().removeAll();
		signIn = new MemberSignInPane(rootLayout,this);
	}
	
	public void addRegisterPane(){
		rootLayout.getChildren().removeAll();
		registerMain = new MemberRegisterMainPane(rootLayout,this);
	}

}
