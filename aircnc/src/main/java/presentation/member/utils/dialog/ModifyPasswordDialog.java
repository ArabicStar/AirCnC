package presentation.member.utils.dialog;

import java.util.Optional;

import interactor.impl.member.MemberInfoCourier;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;

public class ModifyPasswordDialog {

	public ModifyPasswordDialog() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("修改密码");
		dialog.setHeaderText("修改你的密码");

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new
		// ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("确认", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		PasswordField oldPwd = new PasswordField();
		oldPwd.setPromptText("旧密码");
		PasswordField newPwd = new PasswordField();
		newPwd.setPromptText("新密码");
		PasswordField newPwdConfirm = new PasswordField();
		newPwdConfirm.setPromptText("新密码");

		grid.add(new Label("旧密码:"), 0, 0);
		grid.add(oldPwd, 1, 0);
		grid.add(new Label("新密码:"), 0, 1);
		grid.add(newPwd, 1, 1);
		grid.add(new Label("确认你的密码:"), 0, 2);
		grid.add(newPwdConfirm, 1, 2);

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		oldPwd.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(oldPwd.getText(), newPwd.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(oldNewPassword -> {
			InfoModifyAccessorImpl.getInstance().setOldPassword(oldNewPassword.getKey());
			InfoModifyAccessorImpl.getInstance().setNewPassword(oldNewPassword.getValue());
			MemberInfoCourier.getInstance().updatePassword();
			// System.out.println("Old=" + oldNewPassword.getKey() + ", New=" +
			// oldNewPassword.getValue());
			// PlainDialog alert = new
			// PlainDialog(AlertType.INFORMATION,"修改成功","已成功修改密码");
			// alert.showDialog();
		});
	}

}
