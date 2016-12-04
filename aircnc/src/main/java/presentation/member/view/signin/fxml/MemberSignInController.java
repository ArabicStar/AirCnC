package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	
	private Stage stage;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setText("");
		password.setText("");
		
		
	}
	
	/**
	 * handle the button action (Confirm)
	 * check the input value
	 * if the value is valid, jump to main client.
	 * otherwise, pop up error message.
	 */
	@FXML
	public void handleConfirm(){
		System.out.println(username.getText());
		System.out.println(password.getText());
		//按下登陆键，验证正确性，正确则跳只memberMainPane，错误跳出对话框
		if(username.getText().length()!=0&&password.getText().length()!=0){
			//验证正确性
			controller.initializeClient();

		}else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("登录失败");
			alert.setHeaderText(null);
			alert.setContentText("请输入你的用户名和密码！");

			alert.showAndWait();
			
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
	 * @param primaryStage
	 */
	public void setStage(Stage primaryStage){
		this.stage = primaryStage;
	}


}
