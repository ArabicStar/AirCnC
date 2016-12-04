package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;
import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.manager.UserInfoManager;

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
	
	private InfoModifyAccessor accessor;
	
	private UserInfoManager manager;

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
	
	/**
	 * set the controller
	 * @param controller
	 */
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}
	
	/**
	 * set the accessor
	 * @param controller
	 */
	public void setAccessor(InfoModifyAccessor accessor){
		this.accessor = accessor;
	}
	
	/**
	 * set the controller
	 * @param controller
	 */
	public void setManager(UserInfoManager manager){
		this.manager = manager;
	}
	
}
