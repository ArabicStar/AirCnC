package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.member.CenterController;
import presentation.member.ClientCenterController;

public class MemberInfoMainController implements Initializable{
	
	@FXML
	private Button modify;
	
	@FXML
	private Label username;
	
	@FXML
	private Label name;
	
	@FXML
	private Label tele;
	
	@FXML
	private Label mobi;
	
	@FXML
	private Label email;
	
	@FXML
	private Label credit;
	
	private ClientCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleModify(){
		controller.addInfoModifyPane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(ClientCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
}
