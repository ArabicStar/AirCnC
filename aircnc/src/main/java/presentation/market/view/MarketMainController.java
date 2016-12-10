package presentation.market.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.market.MarketCenterController;

public class MarketMainController implements Initializable {
	private static int count = 0;
	
	private static int count1 = 0;
	
	private static int count2 = 0;
	
	private static int count3 = 0;

	@FXML
	private Label websitePromotionStrategy;

	@FXML
	private Label abnormalOrderBrowse;

	@FXML
	private Label creditTopup;

	@FXML
	private Label myOrder;


	private MarketCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(MarketCenterController centerController) {
		this.controller = centerController;
	}
	
	
	@FXML
	private void handleWebsitePromotionStrategy(){
		count++;
		websitePromotionStrategy.setText(Integer.toBinaryString(count));
		this.controller.addWebsitePromotionStrategyPane();
	}
	
	@FXML
	private void handleAbnormalOrderBrowse(){
		count1++;
		abnormalOrderBrowse.setText(Integer.toBinaryString(count1));
		this.controller.addAbnormalOrderBrowsePane();
	}
	
	@FXML
	private void handleCreditTopup(){
		count2++;
		creditTopup.setText(Integer.toBinaryString(count2));
		this.controller.addCreditTopUpPane();
	}
	
	@FXML
	private void handleMyOrder() {
		count3++;
		myOrder.setText(Integer.toBinaryString(count3));
		this.controller.addMyOrderPane();
	}

}
