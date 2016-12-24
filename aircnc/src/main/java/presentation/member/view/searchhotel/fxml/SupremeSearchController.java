package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import interactor.impl.hotel.HotelSearchCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.member.accessor.HotelSearchAccessor;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.utils.dialog.PlainDialog;

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
	private ComboBox<Integer> lowStar;
	
	@FXML
	private ComboBox<Integer> highStar;
	
	@FXML
	private Button search;
	
	private ToggleGroup isEmpty;
	
	private MemberSearchHotelController controller;
	private HotelSearchAccessor accessor;
	
	/**
	 * contains:
	 * 1.initialize the value.
	 * 2.time selection logic
	 * set the time of checking in today as default
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
						  "所有", "单人间","双人间","家庭间","其他");
				  lowStar.getItems().addAll(
						  1,2,3,4,5,6,7);
				  highStar.getItems().addAll(
						  1,2,3,4,5,6,7);
				  lowStar.valueProperty().addListener((observable, oldValue, newValue) -> {
					  if(oldValue!=newValue){
						   highStar.getItems().clear();
					   } 
					  	int rangeDown = lowStar.getValue(); 
					  	for(int i = rangeDown; i <= 7; i++){
					  		highStar.getItems().add(i);
					  	}
					  	highStar.setValue(rangeDown);
					});
				  
				  
				//默认类型,把默认改为今天
				  year.setValue(defaultYear);   
				  month.setValue(defaultMonth);   
				  day.setValue(defaultDay);
				  RoomType.setValue("所有");
				  all.setSelected(true);
				  lowStar.setValue(1);
				  highStar.setValue(7);
				  
				  
				  isEmpty = new ToggleGroup();
					all.setToggleGroup(isEmpty);
					onlyEmpty.setToggleGroup(isEmpty);
			  }
			  
		});
		accessor = HotelSearchAccessorImpl.getInstance();
		
	}
	
	@FXML
	public void close(){
		controller.removeSupremeSearch();
	}
	
	/**
	 * deliver the search requirement to the logic layer.
	 * contains: time of checking in, price range, room type, grade, star
	 */
	@FXML
	public void HandleSearch(){
		if(checkGrade()&&checkPrice()){
			
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
			accessor.setStarRange(lowStar.getValue(),highStar.getValue());
			HotelSearchCourier.getInstance().searchByCondition();
			controller.removeSupremeSearch();
			controller.initResult();
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
}
