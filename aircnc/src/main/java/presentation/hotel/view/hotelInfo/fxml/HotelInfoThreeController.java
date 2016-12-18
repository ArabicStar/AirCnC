package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import presentation.hotel.manager.HotelCommentManager;
import presentation.hotel.manager.impl.HotelCommentManagerImpl;
import presentation.hotel.view.hotelInfo.HotelInfoController;

public class HotelInfoThreeController implements Initializable{
	private HotelInfoController controller;
	
	private HotelCommentManager manager;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(!HotelCommentManagerImpl.isLaunched()){
			HotelCommentManagerImpl.launch();
		}
		manager = HotelCommentManagerImpl.getInstance();
	}
	
	@FXML
	public void handlePage1(){
		controller.addHotelInfoOnePane();
	}
	
	@FXML
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	public void setInfoMainController(HotelInfoController controller){
		this.controller=controller;
	}
}
