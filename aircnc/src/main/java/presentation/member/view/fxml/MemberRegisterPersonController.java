package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;

public class MemberRegisterPersonController implements Initializable{
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void handleConfirm(){
		//将数据传入逻辑层，跳转至memberSignInPane
	}
	
}
