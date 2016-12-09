package presentation.member.view.signin.fxml;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import presentation.member.CenterController;
import presentation.member.accessor.RegisterPersonAccessor;
import presentation.member.utils.PlainDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

/**
 * the controller of personal register pane.
 * @author paranoia
 *
 */
public class MemberRegisterPersonController implements Initializable{
	
	@FXML 
	private Label birthday;
	
	@FXML
	private Label year;
	
	@FXML
	private ComboBox<Integer> chooseYear;
	
	@FXML 
	private Label month;
	
	@FXML
	private ComboBox<Integer> chooseMonth;
	
	@FXML
	private Label day;
	
	@FXML
	private ComboBox<Integer> chooseDay;
	
	@FXML
	private Button confirm;
	
	private CenterController controller;
	
	private RegisterPersonAccessor accessor;
	
	/**
	 * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseMonth.setDisable(true);
		chooseDay.setDisable(true);
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  chooseYear.getItems().addAll(
						  1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,
						  1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,
				          1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,
				          2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,
				          2010,2011,2012,2013,2014,2015,2016
				        );
				  chooseMonth.getItems().addAll(  
				            1,2,3,4,5,6,7,8,9,10,11,12  
				        );
				  chooseYear.valueProperty().addListener((observable, oldValue, newValue) -> {
					   chooseMonth.setDisable(newValue==null);
					});
				  
				  chooseMonth.valueProperty().addListener((observable, oldValue, newValue) -> {
					   chooseDay.setDisable(newValue==null);
					   if(oldValue!=newValue){
						   chooseDay.getItems().clear();
					   }
					   if(!chooseDay.isDisable()){
						   Calendar time=Calendar.getInstance(); 
						   time.clear(); 
						   time.set(Calendar.YEAR,chooseYear.getValue()); 
						   //year年
						   time.set(Calendar.MONTH,chooseMonth.getValue()-1);
						   //Calendar对象默认一月为0,month月            
						   int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
						   for(int i=1;i<=day;i++){
							   chooseDay.getItems().add(i);
						   }
					   }
					});

			  }
		});	
		//use "new Separator" to create subitems.
	}
	
	/**
	 * handle the button action (Confirm)
	 * check the input value
	 * if the value is valid, jump to sign in pane (MemberSignInPane).
	 * otherwise, pop up error message.
	 */
	@FXML
	public void handleConfirm(){
		if(chooseYear.getValue()!=null && chooseMonth.getValue()!=null && chooseDay.getValue()!=null){
			accessor.setBirthday(LocalDate.of(chooseYear.getValue(), chooseMonth.getValue(), chooseDay.getValue()));
			controller.addSignInPane();
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
					"注册失败","请输入你的完整的生日信息");
			alert.showDialog();
		}
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(CenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
	
	/**
	 * set the accessor
	 * @param accessor
	 */
	public void setAccessor(RegisterPersonAccessor accessor) {
		// TODO Auto-generated method stub
		this.accessor=accessor;
	}
	
}
