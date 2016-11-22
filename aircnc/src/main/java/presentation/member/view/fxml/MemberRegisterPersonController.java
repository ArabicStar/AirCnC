package presentation.member.view.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.member.CenterController;
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
	
	private CenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//add the content of chioces
		chooseYear.setItems(FXCollections.observableArrayList(1990,1991,1992,1993,1994,
				1995));
		chooseMonth.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
		chooseDay.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
		//use "new Separator" to create subitems.
	}
	
	@FXML
	public void handleConfirm(){
		//将数据传入逻辑层，跳转至memberSignInPane
	}
	
	public void setCenterController(CenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
	
}
