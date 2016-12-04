package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.member.CenterController;
import presentation.member.accessor.RegisterEnterpriseAccessor;
import presentation.member.utils.PlainDialog;

/**
 * the controller of business register pane.
 * @author paranoia
 *
 */
public class MemberRegisterEnterpriseController implements Initializable{
	
	@FXML
	private TextField enterprise;
	
	@FXML
	private Button confirm;
	
	private CenterController controller;
	
	private RegisterEnterpriseAccessor accessor;

	/**
	 * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enterprise.setText("企业名称");
		//监听键入
	}
	
	/**
	 * handle the button action (Confirm)
	 * check the input
	 * if input is valid, jump to sign in pane.
	 * otherwise, pop up the error message.
	 */
	@FXML
	public void handleConfirm(){
		if(enterprise.getText().length()>0){
			accessor.setEnterprise(enterprise.getText());
			controller.addSignInPane();
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"注册失败","请输入你的企业名称");
			alert.showDialog();
		}
		
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController centerController){
		this.controller = centerController;
	}
	
	/**
	 * set the accessor
	 * @param accessor
	 */
	public void setAccessor(RegisterEnterpriseAccessor accessor){
		this.accessor = accessor;
	}
}
