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
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
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

	}

	public void addInfoMainPane() {
		MemberInfoManagerImpl.launch();
		content.getChildren().clear();
		infoMain = new MemberInfoMainPane();
		content.getChildren().add(infoMain.getContentPane());
		AnchorPane.setTopAnchor(infoMain.getContentPane(),10.0);
		infoMain.getController().setCenterController(this);
		
	}

	public void addInfoModifyPane() {
		InfoModifyAccessorImpl.launch();
		content.getChildren().clear();
		infoModify = new MemberInfoModifyPane();
		content.getChildren().add(infoModify.getPane());
		AnchorPane.setTopAnchor((infoModify.getPane()), 10.0);
		//(infoModify.getPane());
		infoModify.getController().setCenterController(this);
	}

	public void addCreditChangePane() {
		CreditChangeManagerImpl.launch();
		content.getChildren().clear();
		creditMain = new MemberCreditChangePane();
		content.getChildren().add(creditMain.getPane());
		AnchorPane.setTopAnchor(creditMain.getPane(), 10.0);
		creditMain.getController().setCenterController(this);
		//addCreditChangeRecord();
	}

	public void addSearchHotelPane() {
		SearchHotelManagerImpl.launch();
		content.getChildren().clear();
		searchMain = new MemberSearchHotelPane();
		content.getChildren().add(searchMain.getPane());
		searchMain.getController().setCenterController(this);
		searchMain.getController().setRootLayout(content);
	}
	
	public void addOrderMainPane() {
		MyOrderManagerImpl.launch();
		content.getChildren().clear();
		orderMain = new MemberOrderMainPane();
		content.getChildren().add(orderMain.getPane());
		orderMain.getController().setCenterController(this);
	}

}
