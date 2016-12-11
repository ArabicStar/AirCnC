package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.member.CenterController;
import presentation.member.accessor.MemberLoginAccessor;
import presentation.member.utils.PlainDialog;

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
	
	private MemberLoginAccessor accessor;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setPromptText("用户名");
		password.setPromptText("密码");
	}
	
	/**
	 * handle the button action (Confirm)
	 * check the input value
	 * if the value is valid, jump to main client.
	 * otherwise, pop up error message.
	 */
	@FXML
	public void handleConfirm(){
		if(username.getText().length()!=0&&password.getText().length()!=0){
			accessor.setDeliveredId(username.getText());
			accessor.setDeliveredPassword(password.getText());
			
			//use valid to mark whether it is correct
			boolean valid = true;
			
			if(valid)
				controller.initializeClient();
			else{
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
						"登录失败","错误的用户名或密码！");
				alert.showDialog();
			}
				

		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"登录失败","请输入你的用户名和密码");
			alert.showDialog();			
		}		
		
	}
	
	/**
	 * handle the button action (Register)
	 * jump to the register pane.
	 */
	@FXML
	public void handleRegister(){
		//注册跳转按钮，跳转至memberRegisterMainPane
		controller.addRegisterPane();
		
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController controller){
		this.controller=controller;
	}
	
	/**
	 * set the stage
	 * @param accessor
	 */	
	public void setAccessor(MemberLoginAccessor accessor){
		this.accessor = accessor;
	}


}
