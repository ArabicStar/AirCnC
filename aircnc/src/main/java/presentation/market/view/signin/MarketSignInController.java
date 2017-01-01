package presentation.market.view.signin;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import interactor.impl.market.MarketAccountCourier;
import interactor.market.MarketAccountInfoInteractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.market.CenterController;
import presentation.market.accessor.MarketLoginAccessor;
import presentation.market.accessor.impl.MarketLoginAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;

public class MarketSignInController implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button confirm;

	private CenterController controller;
	
	private MarketLoginAccessor accessor;
	
	private MarketAccountInfoInteractor interactor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accessor = MarketLoginAccessorImpl.getInstance();
		interactor = MarketAccountCourier.getInstance();

	}
	
	@FXML
	private void handleCloseWindow(){
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,"退出客户端","确认退出客户端吗？");
		Optional<ButtonType> result = alert.showDialog();
		
		if(result.get() == ButtonType.OK){
			this.controller.closeWindow();
		}
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
			boolean valid = interactor.login();
			
			if(valid)
				controller.initializeClient();		
		}
	}

}
