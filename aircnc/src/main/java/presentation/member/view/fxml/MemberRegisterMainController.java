package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import presentation.member.CenterController;

/**
 * this is the controller of register main pane.
 * @author paranoia
 *
 */

public class MemberRegisterMainController implements Initializable{
	
	@FXML
    private RadioButton personal;
	
	@FXML
    private RadioButton business;
	
	@FXML
    private TextField username;

	@FXML
    private PasswordField password;

	@FXML
    private PasswordField confirmPassword;
	
	@FXML
	private Button next;
	
	private ToggleGroup memberType;
	
	private CenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberType = new ToggleGroup();
		personal.setToggleGroup(memberType);
		business.setToggleGroup(memberType);	
	}
	
	@FXML
	public void handleConfirmPassword(){
		//验证用户填写的正确性，正确就将内容传到逻辑层，逻辑层存入数据库，然后跳至下一个注册界面
		//检查单选的选择，选择1则跳至memberRegsiterPerson，选择2则跳至memberRegisterBusiness
//		if(password.getText().equals(confirmPassword.getText())){
			controller.addRegisterPersonPane();
//		}
	}

	public void setCenterController(CenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
}
