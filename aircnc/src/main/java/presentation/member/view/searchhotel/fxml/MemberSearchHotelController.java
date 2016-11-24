package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;

/**
 * the controller of hotel search (main).
 * @author paranoia
 *
 */
public class MemberSearchHotelController implements Initializable{
	
	@FXML
	private TextField hotelName;
	
	@FXML
	private Label supreme;
	
	private ClientCenterController controller;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleSupreme(){
		
	}
	
}
