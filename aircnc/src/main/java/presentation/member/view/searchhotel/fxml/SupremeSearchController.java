package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.member.accessor.SupremeSearchAccessor;
import presentation.member.utils.PlainDialog;

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
	private ComboBox<String> star;
	
	@FXML
	private Button search;
	
	private ToggleGroup isEmpty;
	
	private MemberSearchHotelController controller;
	private SupremeSearchAccessor accessor;
	
	/**
	 * contains:
	 * 1.initialize the value.
	 * 2.time selection logic
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Calendar time=Calendar.getInstance(); 
		//Calendar对象默认一月为0,month月
		int defaultYear = time.get(Calendar.YEAR);
		int defaultMonth = time.get(Calendar.MONTH)+1;
		int defaultDay = time.get(Calendar.DATE);
		  
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  year.getItems().addAll(
						  defaultYear, defaultYear+1
				        );
				  year.valueProperty().addListener((observable, oldValue, newValue) -> {
					  	if(oldValue!=newValue){
							   month.getItems().clear();
							   if(newValue==2016){
								   month.setValue(defaultMonth);
								   day.setValue(defaultDay);
							   }else{
								   month.setValue(1);
								   day.setValue(1);
							   }
						   }
					  	if(year.getValue()==defaultYear){
					  		for(int i = defaultMonth; i <= 12; i++){
					  			month.getItems().add(i);
					  		}
					  	}else{
					  		month.getItems().addAll(  
						            1,2,3,4,5,6,7,8,9,10,11,12  
						        );
					  	}
					});
				  
				  month.valueProperty().addListener((observable, oldValue, newValue) -> {
					   if(oldValue!=newValue){
						   day.getItems().clear();
						   if(year.getValue()==defaultYear&&month.getValue()==defaultMonth){
						  		day.setValue(defaultDay);
						  	}else{
						  		day.setValue(1);
						  	}
					   } 
					   try{
						   Calendar timeSelected=Calendar.getInstance(); 
						   timeSelected.clear(); 
						   timeSelected.set(Calendar.YEAR,year.getValue()); 
						   timeSelected.set(Calendar.MONTH,month.getValue()-1);
						   int dayNum=timeSelected.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
						   if(year.getValue()==defaultYear&&month.getValue()==defaultMonth){
						  		for(int i = defaultDay; i <= dayNum; i++){
						  			day.getItems().add(i);
						  		}
						  	}else{
						  		 for(int i=1;i<=dayNum;i++){
									   day.getItems().add(i);
								   }
						  	}
					   }catch(Exception e){
						   
					   }
					   
					});
				  
				  day.valueProperty().addListener((observable, oldValue, newValue) -> {
					   search.setDisable(newValue==null);
					});
				  RoomType.getItems().addAll(
						  "所有","大床房","双人间","家庭间","其他");
				  star.getItems().addAll(
						  "所有","1","2","3","4","5","6","7");
				  
				//默认类型,把默认改为今天
				  year.setValue(defaultYear);   
				  month.setValue(defaultMonth);   
				  day.setValue(defaultDay);
				  RoomType.setValue("所有");
				  all.setSelected(true);
				  star.setValue("所有");
				  
				  isEmpty = new ToggleGroup();
					all.setToggleGroup(isEmpty);
					onlyEmpty.setToggleGroup(isEmpty);
			  }
			  
		});	
		
	}
	
	@FXML
	public void close(){
		controller.removeSupremeSearch();
	}
	
	@FXML
	public void HandleSearch(){
		if(checkGrade()&&checkPrice()){
			int starSelected = 0;
			
			//deliver the date
			accessor.setYear(year.getValue());
			accessor.setMonth(month.getValue());
			accessor.setDay(day.getValue());
			//
			accessor.setLowPrice(Integer.valueOf(LowPrice.getText()));
			accessor.setHighPrice(Integer.valueOf(HighPrice.getText()));
			//
			accessor.setRoomType(RoomType.getValue());
			accessor.setEmpty(onlyEmpty.isSelected());
			accessor.setGrade(Double.valueOf(grade.getText()));
			
			switch(star.getValue()){
			case "所有": starSelected = 0; break;
			case "1": starSelected = 1; break;
			case "2": starSelected = 2; break;
			case "3": starSelected = 3; break;
			case "4": starSelected = 4; break;
			case "5": starSelected = 5; break;
			case "6": starSelected = 6; break;
			case "7": starSelected = 7; break;
			}
			accessor.setStar(starSelected);
			controller.removeSupremeSearch();
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"搜索失败","请输入完整有效的搜索信息");
			alert.showDialog();
		}
	}
	
	/**
	 * proving correctness of grade user input.
	 * @return boolean
	 */
	public boolean checkGrade(){
		String content = grade.getText();
		try{
			Double.valueOf(content);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * proving correctness of price range user input.
	 * @return boolean
	 */
	public boolean checkPrice(){
		String content1 = LowPrice.getText();
		String content2 = HighPrice.getText();
		int temp1;  int temp2;
		try{
			temp1 = Integer.valueOf(content1);
			temp2 = Integer.valueOf(content2);
			
			if(temp1>temp2)
				return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public void setController(MemberSearchHotelController controller){
		this.controller = controller;
	}
	
	public void setAccessor(SupremeSearchAccessor accessor){
		this.accessor = accessor;
	}
}
