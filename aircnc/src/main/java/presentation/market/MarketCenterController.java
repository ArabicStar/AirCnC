package presentation.market;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.market.view.MarketMainPane;
import presentation.market.view.websitepromotionstrategy.WebsitePromotionStrategyPane;

public class MarketCenterController extends Application {
	private Stage primaryStage;
	private Scene scene;
	private BorderPane rootLayout;

	private MarketMainPane mainClient;
	private WebsitePromotionStrategyPane websitePromotionStrategyPane;

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
	 * 添加网站营销策略
	 */
	public void addWebsitePromotionStrategyPane() {
		clearContent();
		websitePromotionStrategyPane = new WebsitePromotionStrategyPane();
		mainClient.getBorderPane().setCenter(websitePromotionStrategyPane.getPane());
		if(websitePromotionStrategyPane.getController() == null) {
			System.out.println("NULL!!!");
		}
		websitePromotionStrategyPane.getController().setCenterController(this);
		
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
