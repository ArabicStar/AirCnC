package presentation.market.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.market.MarketCenterController;

public class MarketMainController implements Initializable {

	@FXML
	private Label hotelInfo;

	@FXML
	private Label orderExecute;

	@FXML
	private Label orderBrowse;

	@FXML
	private Label checkInLive;

	@FXML
	private Label checkOut;

	@FXML
	private Label abnormalOrder;

	@FXML
	private Label hotelPromotion;

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
		// TODO Auto-generated method stub
		this.controller = centerController;
	}
	
	
	@FXML
	private void handleHotelInfo(){
		// TODO
	}
	
	@FXML
	private void handleOrderExecute(){
		// TODO
	}
	
	@FXML
	private void handleOrderBrowse(){
		// TODO
	}

}
