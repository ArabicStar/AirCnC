package presentation.manage.view.hotelmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.utils.dialog.PlainDialog;

public class HotelAddController implements Initializable {

	@FXML
	private ComboBox<Integer> star;

	@FXML
	private TextField name;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField passwordConfirm;

	@FXML
	private Button confirm;

	private HotelManageMainController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			star.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
			star.setValue(1);
			name.setPromptText("添加名称");
			password.setPromptText("设置酒店密码");
			passwordConfirm.setPromptText("确认酒店密码");
			name.textProperty().addListener((observable, oldValue, newValue) -> {
				confirm.setDisable(newValue.trim().isEmpty());
			});
		});
	}

	@FXML
	public void handleConfirm() {
		if (name.getText().length() > 0 && password.getText().length() > 0 && passwordConfirm.getText().length() > 0) {
			if (password.getText().equals(passwordConfirm.getText())) {
				HotelManageInfoAccessorImpl.getInstance().setName(name.getText());
				HotelManageInfoAccessorImpl.getInstance().setStar(star.getValue());
				HotelManageInfoAccessorImpl.getInstance().setPassword(password.getText());
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

	public void setController(HotelManageMainController controller) {
		this.controller = controller;
	}

}
