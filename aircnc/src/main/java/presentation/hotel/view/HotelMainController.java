package presentation.hotel.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import presentation.hotel.HotelCenterController;
import presentation.member.utils.dialog.PlainDialog;

public class HotelMainController implements Initializable{
	@FXML
	private HBox hotelInfo;
	
	@FXML
	private HBox orderExecute;
	
	@FXML
	private HBox orderBrowse;
	
	@FXML
	private HBox checkInLive;
	
	@FXML
	private HBox checkOut;
	
	@FXML
	private HBox abnormalOrder;
	
	@FXML
	private HBox hotelPromotion;
	
	private HotelCenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	@FXML
	private void handleLogout(){
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,
				"注销确认","确认登出吗？");
		
		Optional<ButtonType> result = alert.showDialog();		
		if(result.get() == ButtonType.OK){
			controller.initializeLogin();
			
		}
	}
	
	@FXML
	private void handleHotelInfo(){
		hotelInfo.setDisable(true);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(false);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(false);
		controller.addHotelInfoMainPane();
	}
	
	@FXML
	private void handleOrderExecute(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(true);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(false);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(false);
		controller.addOrderExecutePane();
	}
	
	@FXML
	private void handleOrderBrowse(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(true);
		checkInLive.setDisable(false);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(false);
		controller.addOrderBrowsePane();
	}
	
	@FXML
	private void handleCheckInLive(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(true);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(false);
		controller.addCheckInLivePane();
	}
	
	@FXML
	private void handleCheckOut(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(false);
		checkOut.setDisable(true);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(false);
		controller.addCheckOutPane();
	}
	
	@FXML
	private void handleAbnormalOrder(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(false);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(true);
		hotelPromotion.setDisable(false);
		controller.addAbnormalOrderPane();
	}
	
	@FXML
	private void handleHotelPromotion(){
		hotelInfo.setDisable(false);
		orderExecute.setDisable(false);
		orderBrowse.setDisable(false);
		checkInLive.setDisable(false);
		checkOut.setDisable(false);
		abnormalOrder.setDisable(false);
		hotelPromotion.setDisable(true);
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
