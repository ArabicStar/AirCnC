package presentation.market.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import interactor.impl.market.MarketAccountCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import presentation.market.MarketCenterController;
import presentation.member.utils.dialog.PlainDialog;

public class MarketMainController implements Initializable {

	@FXML
	private HBox websitePromotion;

	@FXML
	private HBox abnormalOrder;

	@FXML
	private HBox creditCharge;
	
	@FXML
	private HBox levelStrategy;

	private MarketCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				creditCharge.setDisable(true);
				controller.addCreditCharge();
			}
		});
	}
	
	@FXML
	public void handleLogout(){
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,
				"注销确认","确认登出吗？");
		
		Optional<ButtonType> result = alert.showDialog();		
		if(result.get() == ButtonType.OK){
			MarketAccountCourier.getInstance().logout();
			controller.initializeLogin();
		}
	}

	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(MarketCenterController centerController) {
		this.controller = centerController;
	}
	
	
	@FXML
	private void handleWebsitePromotion(){
		websitePromotion.setDisable(true);
		abnormalOrder.setDisable(false);
		creditCharge.setDisable(false);
		levelStrategy.setDisable(false);
		this.controller.addWebsitePromotionPane();
	}
	
	@FXML
	private void handleAbnormalOrder(){
		websitePromotion.setDisable(false);
		abnormalOrder.setDisable(true);
		creditCharge.setDisable(false);
		levelStrategy.setDisable(false);
		this.controller.addAbnormalOrderPane();
	}
	
	@FXML
	private void handleCreditCharge(){
		websitePromotion.setDisable(false);
		abnormalOrder.setDisable(false);
		creditCharge.setDisable(true);
		levelStrategy.setDisable(false);
		this.controller.addCreditCharge();
	}

	@FXML
	private void handleLevelStrategy(){
		websitePromotion.setDisable(false);
		abnormalOrder.setDisable(false);
		creditCharge.setDisable(false);
		levelStrategy.setDisable(true);
		this.controller.addLevelStrategyPane();
	}
	
	@FXML
	private void handleCloseWindow(){
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,"退出客户端","确认退出客户端吗？");
		Optional<ButtonType> result = alert.showDialog();
		
		if(result.get() == ButtonType.OK){
			this.controller.closeWindow();
		}
	}
	
	
}
