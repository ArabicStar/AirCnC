package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import interactor.impl.member.MemberAccountCourier;
import interactor.member.MemberAccountInteractor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.member.CenterController;
import presentation.member.accessor.MemberLoginAccessor;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.MemberLoginAccessorImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.utils.dialog.PlainDialog;

/**
 * this is the controller of sign in pane.
 * 
 * @author paranoia
 *
 */

public class MemberSignInController implements Initializable {

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
	private MemberAccountInteractor interactor;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setPromptText("用户名");
		password.setPromptText("密码");

		accessor = MemberLoginAccessorImpl.getInstance();
	}

	/**
	 * handle the button action (Confirm) check the input value if the value is
	 * valid, jump to main client. otherwise, pop up error message.
	 */
	@FXML
	public void handleConfirm() {
		if (username.getText().length() != 0 && password.getText().length() != 0) {
			accessor.setDeliveredId(username.getText());
			accessor.setDeliveredPassword(password.getText());
			// 下面是逻辑相连后的代码（没开服务器就会报错，暂时注释
			interactor = MemberAccountCourier.getInstance();
			boolean valid = interactor.login();
//			boolean valid = true;
			if (valid){
				InfoModifyAccessorImpl.getInstance().setUser(MemberInfoManagerImpl.getInstance().getMemberVo());
				controller.initializeClient();
			}
		} else {
			new PlainDialog(AlertType.INFORMATION, "登录失败", "请输入你的用户名和密码").showDialog();
		}

	}

	/**
	 * handle the button action (Register) jump to the register pane.
	 */
	@FXML
	public void handleRegister() {
		// 注册跳转按钮，跳转至memberRegisterMainPane
		controller.addRegisterPane();

	}

	/**
	 * set the centerController
	 * 
	 * @param centerController
	 */
	public void setCenterController(CenterController controller) {
		this.controller = controller;
	}
	
	public void handleCloseWindow(){
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,"退出客户端","确认退出客户端吗？");
		Optional<ButtonType> result = alert.showDialog();
		
		if(result.get() == ButtonType.OK){
			this.controller.closeWindow();
		}
	}
}
