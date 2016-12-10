package presentation.market;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.market.view.MarketMainPane;
import presentation.market.view.abnormalorderbrowse.AbnormalOrderBrowsePane;
import presentation.market.view.credittopup.CreditTopUpPane;
import presentation.market.view.myorder.MyOrderPane;
import presentation.market.view.websitepromotionstrategy.WebsitePromotionStrategyPane;

public class MarketCenterController extends Application {
	@SuppressWarnings("unused")
	private Stage primaryStage;
	private Scene scene;
	@SuppressWarnings("unused")
	private BorderPane rootLayout;

	private MarketMainPane mainClient;
	private WebsitePromotionStrategyPane websitePromotionStrategyPane;
	private AbnormalOrderBrowsePane abnormalOrderBrowsePane;
	private CreditTopUpPane creditTopUpPane;
	private MyOrderPane myOrderPane;

	private final static int Client_Width = 1024;
	private final static int Client_Height = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("AirCnC");
		primaryStage.setResizable(false);

		// show the pane of sign in.
		mainClient = new MarketMainPane(primaryStage);
		mainClient.getController().setCenterController(this);
		rootLayout = mainClient.getBorderPane();
		scene = new Scene(mainClient.getBorderPane(), Client_Width, Client_Height);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	/**
	 * 添加网站营销策略界面
	 */
	public void addWebsitePromotionStrategyPane() {
		clearContent();
		websitePromotionStrategyPane = new WebsitePromotionStrategyPane();
		mainClient.getBorderPane().setCenter(websitePromotionStrategyPane.getPane());
		websitePromotionStrategyPane.getController().setCenterController(this);
		// TODO:测试用代码
		websitePromotionStrategyPane.getController().test();
	}
	
	/**
	 * 添加浏览异常订单界面
	 */
	public void addAbnormalOrderBrowsePane() {
		clearContent();
		abnormalOrderBrowsePane = new AbnormalOrderBrowsePane();
		mainClient.getBorderPane().setCenter(abnormalOrderBrowsePane.getPane());
		abnormalOrderBrowsePane.getController().setCenterController(this);
		abnormalOrderBrowsePane.getController().test();
	}
	
	/**
	 * 添加信用充值界面
	 */
	public void addCreditTopUpPane() {
		clearContent();
		creditTopUpPane = new CreditTopUpPane();
		mainClient.getBorderPane().setCenter(creditTopUpPane.getPane());
		creditTopUpPane.getController().setCenterController(this);		
	}
	
	/**
	 * 添加我的订单界面
	 */
	public void addMyOrderPane() {
		clearContent();
		myOrderPane = new MyOrderPane();
		mainClient.getBorderPane().setCenter(myOrderPane.getPane());
		myOrderPane.getController().setCenterController(this);
	}
	
	
	/**
	 * remove all the children nodes of the main border pane, except the
	 * nav-bar.
	 */
	public void clearContent() {
		int childrenAmount = mainClient.getBorderPane().getChildren().size();
		if (childrenAmount > 0) {
			for (int i = 1; i < childrenAmount; i++) {
				mainClient.getBorderPane().getChildren().remove(i);
			}
		}
	}
	
}
