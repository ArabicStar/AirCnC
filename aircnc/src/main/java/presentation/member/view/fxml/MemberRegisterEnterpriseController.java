package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.member.CenterController;

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
		//这里将键入的数据传入逻辑层，跳转至memberSignInPane。
		controller.addSignInPane();
		
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController centerController){
		this.controller=centerController;
	}
}
