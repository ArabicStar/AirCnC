package presentation.hotel.view.signIn;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelAccountInteractor;
import interactor.impl.hotel.HotelAccountCourier;
import interactor.member.MemberAccountInteractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.hotel.CenterController;
import presentation.hotel.accessor.HotelLoginAccessor;
import presentation.hotel.accessor.impl.HotelLoginAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;

public class HotelSignInController implements Initializable{

	@FXML
    private TextField name;

	@FXML
    private PasswordField password;

	@FXML
    private Button confirm;
	
	private CenterController controller;
	
	private HotelLoginAccessor accessor;
	
	private HotelAccountInteractor interactor;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		interactor = HotelAccountCourier.getInstance();
		name.setPromptText("酒店名称");
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
		if(name.getText().length()!=0&&password.getText().length()!=0){

			accessor = HotelLoginAccessorImpl.getInstance();
			accessor.setDeliveredName(name.getText());
			accessor.setDeliveredPassword(password.getText());
			
			//use valid to mark whether it is correct
			boolean valid = interactor.login();
			
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
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController controller){
		this.controller=controller;
	}

}
