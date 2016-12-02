package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.hotel.view.hotelInfo.HotelInfoController;

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
	
	private HotelInfoController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleModify(){
		controller.addHotelModifyPane();
	}
	
	@FXML
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	@FXML
	public void handlePage3(){
		
	}
	
	public void setInfoMainController(HotelInfoController controller){
		this.controller=controller;
	}
	
}
