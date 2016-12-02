package presentation.hotel.view.checkOut.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import presentation.hotel.HotelCenterController;

public class CheckOutController implements Initializable{
	@FXML
	private Button comfirm;
	
	@FXML
	private TextField roomNum;
	
	@FXML
	private ChoiceBox<String> roomType;
	
	private HotelCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleConfirm(){
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
}
