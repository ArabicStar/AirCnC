package presentation.member.view.signin.fxml;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import interactor.impl.member.MemberAccountCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.member.CenterController;
import presentation.member.accessor.RegisterAccessor;
import presentation.member.accessor.impl.RegisterAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;

/**
 * the controller of business register pane.
 * 
 * @author paranoia
 *
 */
public class MemberRegisterEnterpriseController implements Initializable {

	@FXML
	private TextField enterprise;

	@FXML
	private Button confirm;
	
	@FXML
	private Button back;

	private CenterController controller;

	private RegisterAccessor accessor;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enterprise.setPromptText("企业名称");
		confirm.setDisable(true);
		Platform.runLater(() -> {
			confirm.requestFocus();
			// 监听键入,输入的除去空格后有内容则恢复按钮
			enterprise.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
		accessor = RegisterAccessorImpl.getInstance();
	}
	
	@FXML
	public void handleBack(){
		controller.addSignInPane();
	}
	
	/**
	 * handle the button action (Confirm) check the input if input is valid,
	 * jump to sign in pane. otherwise, pop up the error message.
	 */
	@FXML
	public void handleConfirm() {
		PlainDialog alert = new PlainDialog(AlertType.CONFIRMATION,
				"请进行确认","填写的企业将不可更改，确认不再修改吗");
		Optional<ButtonType> result = alert.showDialog();
		
		//这里还要修改
		result.ifPresent(usernamePassword -> {
			accessor.setEnterprise(enterprise.getText());
			MemberAccountCourier.getInstance().register();
			controller.addSignInPane();
			
			PlainDialog registerSuccess = new PlainDialog(AlertType.INFORMATION,
					"注册成功","您的注册ID为");
			registerSuccess.showDialog();
		});
	}

	/**
	 * set the centerController
	 * 
	 * @param centerController
	 */
	public void setCenterController(CenterController centerController) {
		this.controller = centerController;
	}
}
