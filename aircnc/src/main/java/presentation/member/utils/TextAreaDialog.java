package presentation.member.utils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class TextAreaDialog extends GridPane {

	public TextAreaDialog(String content) {
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(0, 10, 0, 10));
		final TextField username = new TextField();
		username.setPromptText("Username");
		final PasswordField password = new PasswordField();
		password.setPromptText("Password");

		this.add(new Label("Username:"), 0, 0);
		this.add(username, 1, 0);
		this.add(new Label("Password:"), 0, 1);
		this.add(password, 1, 1);

		String usernameResult;
		String passwordResult;

//		Callback myCallback = new Callback() {
//
//			@Override
//		public Object call(Object param) {
//			usernameResult = username.getText();
//			passwordResult = password.getText();
//			return null;
//		}
//		};
//
//		DialogResponse resp = Dialogs.showCustomDialog(stage, grid, "Please log in", "Login", DialogOptions.OK_CANCEL,
//				myCallback);
	}
}
