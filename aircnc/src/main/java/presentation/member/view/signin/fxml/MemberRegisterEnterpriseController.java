package presentation.member.view.signin.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import presentation.member.CenterController;
import presentation.member.accessor.RegisterAccessor;
import presentation.member.accessor.impl.RegisterAccessorImpl;

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

	/**
	 * handle the button action (Confirm) check the input if input is valid,
	 * jump to sign in pane. otherwise, pop up the error message.
	 */
	@FXML
	public void handleConfirm() {
		accessor.setEnterprise(enterprise.getText());
		controller.addSignInPane();
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
