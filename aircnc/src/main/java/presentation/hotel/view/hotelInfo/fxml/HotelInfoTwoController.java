package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import presentation.hotel.HotelCenterController;
import presentation.hotel.view.hotelInfo.HotelInfoController;

public class HotelInfoTwoController implements Initializable{

	
	private HotelInfoController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handlePage1(){
		controller.addHotelInfoOnePane();
	}
	
	@FXML
	public void handlePage3(){
		
	}
	
	public void setInfoMainController(HotelInfoController controller){
		this.controller=controller;
	}

}
