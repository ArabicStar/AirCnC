package presentation.market.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.market.MarketCenterController;

public class MarketMainController implements Initializable {
	private static int count = 0;

	@FXML
	private Label websitePromotionStrategy;

	@FXML
	private Label abnormalOrderBrowse;

	@FXML
	private Label creditTopup;



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
		// TODO
	}
	
	@FXML
	private void handleCreditTopup(){
		// TODO
	}

}
