package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.hotel.HotelCenterController;

public class HotelInfoOneController implements Initializable{

	@FXML
	private Label scope;
	
	@FXML
	private Label location;
	
	@FXML
	private Label star;
	
	@FXML
	private Label introduction;
	
	@FXML
	private Label roomType;
	
	@FXML
	private Label roomPrice;
	
	@FXML
	private Label equipment;
	
	@FXML
	private Button page2;
	
	@FXML
	private Button page3;
	
	@FXML
	private Button modify;
	
	private HotelCenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleModify(){
		
	}
	
	@FXML
	public void handlePage2(){
		
	}
	
	@FXML
	public void handlePage3(){
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
}
