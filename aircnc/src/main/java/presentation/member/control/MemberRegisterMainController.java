package presentation.member.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MemberRegisterMainController {
	
	@FXML
    private RadioButton personal;
	
	@FXML
    private RadioButton business;
	
	@FXML
    private TextField username;

	@FXML
    private PasswordField password;

	@FXML
    private PasswordField confirmPassword;
	
	@FXML
	private Button next;
	
	private ToggleGroup memberType;
	
	@FXML
	private void initialize(){
		
	}
}
