package presentation.member;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.member.view.memberinfo.MemberInfoMainPane;
import presentation.member.view.memberinfo.MemberInfoModifyPane;
import presentation.member.view.myorder.MemberOrderMainPane;
import presentation.member.view.searchhotel.MemberSearchHotelPane;
import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.manager.CreditChangeManager;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.UserInfoManager;
import presentation.member.view.MemberMainPane;
import presentation.member.view.creditchange.MemberCreditChangePane;

/**
 * the controller of the whole client.
 * 
 * @author paranoia
 *
 */
public class ClientCenterController extends Application {

	private Stage primaryStage;
	private Scene scene;
	private MemberMainPane mainClient;
	private MemberInfoMainPane infoMain;
	private MemberInfoModifyPane infoModify;
	private MemberCreditChangePane creditMain;
	private MemberSearchHotelPane searchMain;
	private MemberOrderMainPane orderMain;
	
	private UserInfoManager memberInfoManager;
	private MyOrderManager myOrderManager;
	private CreditChangeManager creditManager;
	private SearchHotelManager searchManager;
	
	private InfoModifyAccessor memberInfoAccessor;
	
	private MemberTest test;

	private AnchorPane rootLayout;
	private AnchorPane content;

	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");

		// show the pane of sign in.
		mainClient = new MemberMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getAnchorPane();
		content = (AnchorPane)rootLayout.getChildren().get(1);
		scene = new Scene(mainClient.getAnchorPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);

		// addSignInPane();

		primaryStage.show();
		
		test = new MemberTest();

	}

	public void addInfoMainPane() {
		memberInfoManager = test.getUserData();
		content.getChildren().clear();
		infoMain = new MemberInfoMainPane();
		content.getChildren().add(infoMain.getContentPane());
		AnchorPane.setTopAnchor(infoMain.getContentPane(),10.0);
		infoMain.getController().setCenterController(this);
		infoMain.getController().setManager(memberInfoManager);
		
	}

	public void addInfoModifyPane() {
		content.getChildren().clear();
		memberInfoAccessor = new InfoModifyAccessorImpl();
		infoModify = new MemberInfoModifyPane();
		content.getChildren().add(infoModify.getPane());
		AnchorPane.setTopAnchor((infoModify.getPane()), 10.0);
		//(infoModify.getPane());
		infoModify.getController().setCenterController(this);
		infoModify.getController().setAccessor(memberInfoAccessor);
		infoModify.getController().setManager(memberInfoManager);
	}

	public void addCreditChangePane() {
		creditManager = test.getCreditData();
		content.getChildren().clear();
		creditMain = new MemberCreditChangePane();
		content.getChildren().add(creditMain.getPane());
		AnchorPane.setTopAnchor(creditMain.getPane(), 10.0);
		creditMain.getController().setCenterController(this);
		creditMain.getController().setManager(creditManager);
		//addCreditChangeRecord();
	}
	
	public void addCreditChangeRecord(){
		creditMain.getController().init();
	}

	public void addSearchHotelPane() {
		searchManager = test.getSearchedData();
		content.getChildren().clear();
		searchMain = new MemberSearchHotelPane();
		content.getChildren().add(searchMain.getPane());
		searchMain.getController().setCenterController(this);
		searchMain.getController().setManager(searchManager);
		searchMain.getController().setRootLayout(content);
	}
	
	public void addOrderMainPane() {
		myOrderManager = test.getMyOrderData();
		content.getChildren().clear();
		orderMain = new MemberOrderMainPane();
		content.getChildren().add(orderMain.getPane());
		orderMain.getController().setCenterController(this);
		orderMain.getController().setManager(myOrderManager);
	}
	
	public void setMemberInfoManager(UserInfoManager manager){
		this.memberInfoManager = manager;
	}

}
