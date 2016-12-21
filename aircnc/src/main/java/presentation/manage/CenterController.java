package presentation.manage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import presentation.manage.view.ManageMainPane;
import presentation.manage.view.hotelmanage.HotelManageMainPane;
import presentation.manage.view.marketmanage.MarketManageMainPane;
import presentation.manage.view.membermanage.MemberManageMainPane;

/**
 * the whole stage. contains the connection of every pane
 * 
 * @author paranoia
 *
 */

public class CenterController extends Application {

	private Stage primaryStage;
	
	private Scene scene;
	private AnchorPane content;
	private AnchorPane rootLayout;
	
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

		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		start = new ManageMainPane(primaryStage);
		start.getController().setCenterController(this);
		rootLayout = start.getAnchorPane();
		content = (AnchorPane)rootLayout.getChildren().get(1);
		scene = new Scene(start.getAnchorPane(), Main_Width, Main_Height);
		primaryStage.setScene(scene);

		addMemberManagePane();
		start.getController().setMemberManageDisable();

		primaryStage.show();
	}

	/**
	 * add the pane of member manage
	 */
	public void addMemberManagePane() {
		if(!MemberManageInfoAccessorImpl.isLaunched())
			MemberManageInfoAccessorImpl.launch();
		if(!MemberManageInfoImpl.isLaunched())
			MemberManageInfoImpl.launch();
		content.getChildren().clear();
		memberManage = new MemberManageMainPane(primaryStage);
		content.getChildren().add(memberManage.getAnchorPane());
		AnchorPane.setTopAnchor(memberManage.getAnchorPane(), 0.0);
		memberManage.getController().setCenterController(this);
		memberManage.getController().setRootLayout(content);
	}
	
	/**
	 * add the pane of hotel manage
	 */
	public void addHotelManagePane() {
		if(!HotelManageInfoAccessorImpl.isLaunched())
			HotelManageInfoAccessorImpl.launch();
		if(!HotelManageInfoManagerImpl.isLaunched())
			HotelManageInfoManagerImpl.launch();
		content.getChildren().clear();
		hotelManage = new HotelManageMainPane(primaryStage);
		content.getChildren().add(hotelManage.getAnchorPane());
		AnchorPane.setTopAnchor(hotelManage.getAnchorPane(), 0.0);
		hotelManage.getController().setCenterController(this);
	}
	
	/**
	 * add the pane of market manage
	 */
	public void addMarketManagePane() {
		if(!MarketManageInfoAccessorImpl.isLaunched())
			MarketManageInfoAccessorImpl.launch();
		if(!MarketManageInfoManagerImpl.isLaunched())
			MarketManageInfoManagerImpl.launch();
		content.getChildren().clear();
		marketManage = new MarketManageMainPane(primaryStage);
		content.getChildren().add(marketManage.getAnchorPane());
		AnchorPane.setTopAnchor(marketManage.getAnchorPane(), 0.0);
		marketManage.getController().setCenterController(this);
	}
	

}
