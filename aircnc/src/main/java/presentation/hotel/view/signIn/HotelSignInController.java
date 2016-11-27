package presentation.hotel.view.signIn;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.member.CenterController;

public class HotelSignInController implements Initializable{

	@FXML
    private TextField username;

	@FXML
    private PasswordField password;

	@FXML
    private Button confirm;
	
	private CenterController controller;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		username.setText("admin1");
		password.setText("123456");
		
		
	}
	
	/**
	 * handle the button action (Confirm)
	 * check the input value
	 * if the value is valid, jump to main client.
	 * otherwise, pop up error message.
	 */
	@FXML
	public void handleConfirm(){
		//按下登陆键，验证正确性，正确则跳只hotelMainPane，错误跳出对话框
		controller.initializeClient();
	}

}
