package presentation.member.utils.dialog;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class TextFieldDialog {
	
	private Dialog<String> dialog;
	private TextField textContent;
	
	public TextFieldDialog(String title,String content) {
		// Create the custom dialog.
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
		textContent = new TextField();

		// Set the icon (must be included in the project).
		//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("确定", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		textContent.setPrefColumnCount(20);
		
		grid.add(textContent,1,1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		textContent.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> textContent.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new String(textContent.getText());
		    }
		    return null;
		});
	}
	
	public Optional<String> showDialog(){
		return dialog.showAndWait();
	}
}
