package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import presentation.member.accessor.SupremeSearchAccessor;

public class SupremeSearchController implements Initializable{
	
	@FXML
	private ComboBox<Integer> year;
	
	@FXML
	private ComboBox<Integer> month;
	
	@FXML
	private ComboBox<Integer> day;
	
	@FXML
	private TextField LowPrice;
	
	@FXML 
	private TextField HighPrice;
	
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
	
	private MemberSearchHotelController controller;
	private SupremeSearchAccessor accessor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void close(){
		controller.removeSupremeSearch();
	}
	
	public void HandleSearch(){
		
	}
	
	public void setController(MemberSearchHotelController controller){
		this.controller = controller;
	}
	
	public void setAccessor(SupremeSearchAccessor accessor){
		this.accessor = accessor;
	}
}
