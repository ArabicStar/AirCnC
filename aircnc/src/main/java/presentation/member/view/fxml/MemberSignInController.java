package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import presentation.member.CenterController;

/**
 * this is the controller of sign in pane.
 * @author paranoia
 *
 */

public class MemberSignInController implements Initializable{

	@FXML
    private TextField username;

	@FXML
    private PasswordField password;

	@FXML
    private Button confirm;
	
	@FXML
	private Button register;
	
	private CenterController controller;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setText("admin1");
		password.setText("123456");
		
		
	}
	
	@FXML
	public void handleConfirm(){
		//按下登陆键，验证正确性，正确则跳只memberMainPane，错误跳出对话框
	}
	
	@FXML
	public void handleRegister(){
		//注册跳转按钮，跳转至memberRegisterMainPane
		register.setText("其实你笨");
		controller.addRegisterPane();
		
	}
	
	public void setCenterController(CenterController controller){
		this.controller=controller;
	}


}
