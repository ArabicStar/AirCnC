package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SupremeSearchController implements Initializable{
	
	@FXML
	private ComboBox<Integer> year;
	
	@FXML
	private ComboBox<Integer> month;
	
	@FXML
	private ComboBox<Integer> day;
	
	@FXML
	private Label LowPrice;
	
	@FXML 
	private Label HighPrice;
	
	@FXML 
	private ComboBox<String> RoomType;
	
	@FXML
	private RadioButton all;
	
	@FXML
	private RadioButton onlyEmpty;
	
	@FXML
	private TextField grade;
	
	@FXML
	private ComboBox<Integer> star;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void close(){
		
	}
	
	public void HandleSearch(){
		
	}

}
