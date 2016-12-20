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
import presentation.member.accessor.impl.CreditChangeOrderAccessorImpl;
import presentation.member.accessor.impl.HotelNameAccessorImpl;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.accessor.impl.RegisterAccessorImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.accessor.impl.SupremeSearchAccessorImpl;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.HistoryOrderManagerImpl;
import presentation.member.manager.impl.HotelCommentManagerImpl;
import presentation.member.manager.impl.HotelPromotionManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
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
	
	private Scene scene;

	private final static int Login_Width = 550;
	private final static int Login_Height = 385;
	private MemberTest test;

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
		
//		InfoModifyAccessorImpl.launch();
//		MemberAppealAccessorImpl.launch();
//		MemberCommentAccessorImpl.launch();
//		MemberLoginAccessorImpl.launch();
//		MemberOrderOperationAccessorImpl.launch();
//		RegisterAccessorImpl.launch();
//		SearchOrderInfoAccessorImpl.launch();
//		SupremeSearchAccessorImpl.launch();
//		CreditChangeOrderAccessorImpl.launch();
//		HotelNameAccessorImpl.launch();
//		
//		CreditChangeManagerImpl.launch();
//		MemberInfoManagerImpl.launch();
//		MyOrderManagerImpl.launch();
//		SearchHotelManagerImpl.launch();
//		HotelCommentManagerImpl.launch();
//		HotelPromotionManagerImpl.launch();
//		HistoryOrderManagerImpl.launch();
//		
//		test = new MemberTest();
//		test.getSearchedData();
//		test.getMyOrderData();
//		test.getCreditData();
//		test.getUserData();
		
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
		URL location = getClass().getResource("/images/member/register/login_BG.png");
		start.getBorderPane().setStyle("-fx-background-image: url("+location+");");
		start.getBorderPane().getChildren().clear();
		signIn = new MemberSignInPane();
		start.getBorderPane().setCenter(signIn.getPane());
		signIn.getController().setCenterController(this);
	}

	/**
	 * add the pane of register (MemberRegisterMainPane)
	 */
	public void addRegisterPane() {
		URL location = getClass().getResource("../../images/member/register/register_BG.png");
		start.getBorderPane().setStyle("-fx-background-image: url("+location+");");
		start.getBorderPane().getChildren().clear();
		registerMain = new MemberRegisterMainPane();
		start.getBorderPane().setCenter(registerMain.getPane());
		registerMain.getController().setCenterController(this);
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
