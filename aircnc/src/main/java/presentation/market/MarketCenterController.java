package presentation.market;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.hotel.CenterController;
import presentation.market.view.MarketMainPane;
import presentation.market.view.abnormalorderbrowse.AbnormalOrderBrowsePane;
import presentation.market.view.credittopup.CreditTopUpPane;
import presentation.market.view.levelStrategy.LevelStrategyPane;
import presentation.market.view.websitePromotion.WebsitePromotionPane;

public class MarketCenterController extends Application {
	@SuppressWarnings("unused")
	private Stage primaryStage;
	private Scene scene;
	@SuppressWarnings("unused")
	private BorderPane rootLayout;

	private MarketMainPane mainClient;
	private WebsitePromotionPane websitePromotionPane;
	private AbnormalOrderBrowsePane abnormalOrderBrowsePane;
	private CreditTopUpPane creditTopUpPane;
	private LevelStrategyPane levelPane;

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
	public void addWebsitePromotionPane() {
		clearContent();
		websitePromotionPane = new WebsitePromotionPane();
		mainClient.getBorderPane().setCenter(websitePromotionPane.getPane());
		websitePromotionPane.getController().setCenterController(this);
		websitePromotionPane.getController().setRootLayout(rootLayout);
	}
	
	/**
	 * 添加等级策略界面
	 */
	public void addLevelStrategyPane() {
		clearContent();
		levelPane = new LevelStrategyPane();
		mainClient.getBorderPane().setCenter(levelPane.getPane());
	}
	
	/**
	 * 添加浏览异常订单界面
	 */
	public void addAbnormalOrderPane() {
		clearContent();
		abnormalOrderBrowsePane = new AbnormalOrderBrowsePane();
		mainClient.getBorderPane().setCenter(abnormalOrderBrowsePane.getPane());
		abnormalOrderBrowsePane.getController().setCenterController(this);
	}
	
	/**
	 * 添加信用充值界面
	 */
	public void addCreditCharge() {
		clearContent();
		creditTopUpPane = new CreditTopUpPane();
		mainClient.getBorderPane().setCenter(creditTopUpPane.getPane());
		creditTopUpPane.getController().setCenterController(this);		
	}
	
	public void initializeLogin(){
		primaryStage.close();
		CenterController login = new CenterController();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
