package presentation.manage;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.view.ManageMainPane;
import presentation.manage.view.hotelmanage.HotelManageMainPane;
import presentation.manage.view.marketmanage.MarketManageMainPane;
import presentation.manage.view.membermanage.MemberManageMainPane;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.view.signin.MemberSignInPane;

/**
 * the whole stage. contains the connection of every pane
 * 
 * @author paranoia
 *
 */

public class CenterController extends Application {

	private Stage primaryStage;
	
	private Scene scene;
	
	private ManageMainPane start;
	private MemberManageMainPane memberManage;
	private HotelManageMainPane hotelManage;
	private MarketManageMainPane marketManage;
	

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
		start = new ManageMainPane(primaryStage);
		scene = new Scene(start.getAnchorPane(), Main_Width, Main_Height);
		primaryStage.setScene(scene);

		addMemberManagePane();

		primaryStage.show();
	}

	/**
	 * add the pane of member manage
	 */
	public void addMemberManagePane() {
		if(!MemberManageInfoAccessorImpl.isLaunched())
			MemberManageInfoAccessorImpl.launch();
//		URL location = getClass().getResource("/images/member/register/login_BG.png");
//		start.getBorderPane().setStyle("-fx-background-image: url("+location+");");
//		start.getBorderPane().getChildren().clear();
//		signIn = new MemberSignInPane();
//		start.getBorderPane().setCenter(signIn.getPane());
//		signIn.getController().setCenterController(this);
	}
	
	/**
	 * add the pane of member manage
	 */
	public void addHotelManagePane() {
		
	}
	
	/**
	 * add the pane of member manage
	 */
	public void addMarketManagePane() {
		
	}
	

}
