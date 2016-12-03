package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import presentation.hotel.HotelCenterController;
import presentation.hotel.view.hotelInfo.HotelInfoController;
import presentation.member.ClientCenterController;

public class HotelInfoModifyController implements Initializable{
	@FXML
	private TextField scope;
	
	@FXML
	private TextField location;
	
	@FXML
	private TextArea introduction;	
	
	@FXML
	private Button back;
	
	@FXML
	private Button confirm;
	
	@FXML
	private Button modifyRoom;
	
	private HotelCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleBack(){
		controller.addHotelInfoMainPane();
	}
	
	@FXML
	public void handleConfirm(){
		
	}
	
	@FXML
	public void handleRoomModify(){
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
}