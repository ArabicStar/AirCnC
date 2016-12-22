package presentation.manage.view.marketmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.utils.dialog.PlainDialog;

public class MarketAddController implements Initializable {

	@FXML
	private TextField name;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField passwordConfirm;

	@FXML
	private Button confirm;

	private MarketManageMainController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			name.setPromptText("添加名称");
			password.setPromptText("设置密码");
			passwordConfirm.setPromptText("确认密码");
			name.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
	}

	@FXML
	public void handleConfirm() {
		if (name.getText().length() > 0 && password.getText().length() > 0 && passwordConfirm.getText().length() > 0) {
			if (password.getText().equals(passwordConfirm.getText())) {
				MarketManageInfoAccessorImpl.getInstance().setName(name.getText());
				MarketManageInfoAccessorImpl.getInstance().setPassword(password.getText());
				// 连接
				controller.removeAddHotel();
			} else {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "添加失败", "请输入相同的密码");
				alert.showDialog();
			}
		} else {
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "添加失败", "请输入完整的信息");
			alert.showDialog();
		}
	}

	@FXML
	public void handleClose() {
		controller.removeAddHotel();
	}

	public void setController(MarketManageMainController controller) {
		this.controller = controller;
	}

}
