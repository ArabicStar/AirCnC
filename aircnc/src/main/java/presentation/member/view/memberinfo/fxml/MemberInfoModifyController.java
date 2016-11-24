package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;

public class MemberInfoModifyController implements Initializable{
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField tele;
	
	@FXML
	private TextField phone;
	
	@FXML
	private TextField email;
	
	@FXML
	private Button back;
	
	@FXML
	private Button confirm;
	
	private ClientCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleBack(){
		controller.addInfoMainPane();
	}
	
	@FXML
	public void handleConfirm(){
		
	}
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}
	
}
