package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import presentation.hotel.HotelCenterController;

public class HotelInfoTwoController implements Initializable{

	
	private HotelCenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handlePage1(){
		
	}
	
	@FXML
	public void handlePage3(){
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}

}
