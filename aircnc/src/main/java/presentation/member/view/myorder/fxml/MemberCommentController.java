package presentation.member.view.myorder.fxml;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class MemberCommentController implements Initializable{
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	private Label close;

	private Rating rates;
	
	@FXML
	private TextArea comment;
	
	@FXML
	private Button confirm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rates = new Rating();
		gridPane.add(rates, 0, 1);
		rates.setStyle("-fx-margin: 0 0 0 50px;");
	}
	
}
