package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
		month.setDisable(true);
		day.setDisable(true);
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  RoomType.getItems().addAll(
						  "大床房", "双人间", "三人间", "其他"
					);
				  year.getItems().addAll(
						  2016,2017);
				  year.valueProperty().addListener((observable, oldValue, newValue)->{
					  month.setDisable(newValue==null);
					  month.getItems().addAll(
						1,2,3,4,5,6,7,8,9,10,11,12);
				  });
				  
				  month.valueProperty().addListener((observable, oldValue, newValue) -> {
					   day.setDisable(newValue==null);
					   if(oldValue!=newValue){
						   day.getItems().clear();
					   }
					   if(!day.isDisable()){
						   Calendar time=Calendar.getInstance(); 
						   time.clear(); 
						   time.set(Calendar.YEAR,year.getValue()); 
						   //year年
						   time.set(Calendar.MONTH,month.getValue()-1);
						   //Calendar对象默认一月为0,month月            
						   int dayNum=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
						   for(int i=1;i<=dayNum;i++){
							   day.getItems().add(i);
						   }
					   }
					});
			  }
		});
		
	}
	
	public void close(){
		
	}
	
	public void HandleSearch(){
		
	}

}
