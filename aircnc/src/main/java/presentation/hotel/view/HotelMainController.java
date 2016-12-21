package presentation.hotel.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.hotel.HotelCenterController;

public class HotelMainController implements Initializable{
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
	
	private HotelCenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	@FXML
	private void handleHotelInfo(){
		controller.addHotelInfoMainPane();
	}
	
	@FXML
	private void handleOrderExecute(){
		controller.addOrderExecutePane();
	}
	
	@FXML
	private void handleOrderBrowse(){
		controller.addOrderBrowsePane();
	}
	
	@FXML
	private void handleCheckInLive(){
		controller.addCheckInLivePane();
	}
	
	@FXML
	private void handleCheckOut(){
		controller.addCheckOutPane();
	}
	
	@FXML
	private void handleAbnormalOrder(){
		controller.addAbnormalOrderPane();
	}
	
	@FXML
	private void handleHotelPromotion(){
		controller.addHotelPromotionPane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(HotelCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
	
}
