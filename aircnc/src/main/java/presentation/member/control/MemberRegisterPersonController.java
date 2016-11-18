package presentation.member.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;

public class MemberRegisterPersonController {
	
	@FXML 
	private Label birthday;
	
	@FXML
	private Label year;
	
	@FXML
	private ChoiceBox<Integer> chooseYear;
	
	@FXML 
	private Label month;
	
	@FXML
	private ChoiceBox<Integer> chooseMonth;
	
	@FXML
	private Label day;
	
	@FXML
	private ChoiceBox<Integer> chooseDay;
	
	@FXML
	private Button confirm;
}
