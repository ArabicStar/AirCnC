package presentation.member;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.MemberInfoMainPane;
import presentation.member.view.MemberMainPane;
import presentation.member.view.MemberSignInPane;
import presentation.member.view.MemberStartPane;

public class ClientCenterController extends Application{
	
	private Stage primaryStage;
	private Scene scene;
	private MemberMainPane mainClient;
	private MemberInfoMainPane infoMain;
	
	private BorderPane rootLayout;
	
	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
        primaryStage.setResizable(false);
        
        //show the pane of sign in.
		mainClient = new MemberMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getBorderPane();
        scene = new Scene(mainClient.getBorderPane(),Client_Width,Client_Height);
        primaryStage.setScene(scene);
        
        //addSignInPane();
        
        primaryStage.show();
		
	}
	
	public void addInfoMainPane(){
		mainClient.getBorderPane().getCenter();
		infoMain = new MemberInfoMainPane();
		mainClient.getBorderPane().setCenter(infoMain.getPane());
		infoMain.getController().setCenterController(this);
	}

}
