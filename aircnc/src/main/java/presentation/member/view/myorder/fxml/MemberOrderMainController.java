package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import presentation.member.ClientCenterController;

public class MemberOrderMainController implements Initializable{
	
	private ClientCenterController controller;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
