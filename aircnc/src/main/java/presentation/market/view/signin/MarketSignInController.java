package presentation.market.view.signin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.market.CenterController;

public class MarketSignInController implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button confirm;

	private CenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/**
	 * set the centerController
	 * 
	 * @param centerController
	 */
	public void setCenterController(CenterController controller) {
		this.controller = controller;
	}

	/**
	 * handle the button action (Confirm) check the input value <br>
	 * if the value is valid, jump to main client. <br>
	 * otherwise, pop up error message.<br>
	 */
	@FXML
	public void handleConfirm() {
		// 按下登陆键，验证正确性，正确则跳出marketMainPane，错误跳出对话框
		controller.initializeClient();
	}

}
