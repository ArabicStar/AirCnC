package presentation.member.view.myorder.fxml;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MemberAppealController implements Initializable{
	
	@FXML
	private Label close;
	
	@FXML
	private TextArea comment;
	
	@FXML
	private Button confirm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
