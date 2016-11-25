package presentation.member.view.creditchange.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;

/**
 * the controller of 
 * @author paranoia
 *
 */
public class MemberCreditChangeController implements Initializable{
	
	private ClientCenterController controller;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}