package presentation.manage.utils.dialog;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import interactor.impl.manage.ManageMemberCourier;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.view.membermanage.fxml.MemberManageMainController;
import presentation.member.utils.dialog.PlainDialog;
import vo.member.MemberVo;

public class MemberModifyDialog {

	private Dialog<LocalDate> dialog1;
	private Dialog<String> dialog2;
	ButtonType ConfirmButtonType;
	private GridPane grid;
	private MemberVo vo;
	private MemberManageMainController controller;

	public MemberModifyDialog(MemberVo vo, MemberManageMainController controller) {
		this.vo = vo;
		if (vo.getType().toUpperCase().equals("BUSINESS"))
			initBusinessMemberDialog();
		else
			initPersonMemberDialog();
		this.controller = controller;

	}

	private void initPersonMemberDialog() {
		// Create the custom dialog.
		dialog1 = new Dialog<>();
		dialog1.setTitle("修改客户信息");
		dialog1.setHeaderText("填写修改的信息");

		ConfirmButtonType = new ButtonType("确认", ButtonData.OK_DONE);
		dialog1.getDialogPane().getButtonTypes().addAll(ConfirmButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ComboBox<Integer> newYear = new ComboBox<Integer>();
		newYear.setValue(vo.getBirthday().getYear());
		newYear.setPrefWidth(100);
		ComboBox<Integer> newMonth = new ComboBox<Integer>();
		newMonth.setValue(vo.getBirthday().getMonthValue());
		newMonth.setPrefWidth(100);

		ComboBox<Integer> newDay = new ComboBox<Integer>();
		newDay.setValue(vo.getBirthday().getDayOfMonth());
		newDay.setPrefWidth(100);
		
		newYear.getItems().addAll(
				  1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,
				  1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,
		          1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,
		          2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,
		          2010,2011,2012,2013,2014,2015,2016
		        );
		newMonth.getItems().addAll(  
		            1,2,3,4,5,6,7,8,9,10,11,12  
		        );
		  
		newMonth.valueProperty().addListener((observable, oldValue, newValue) -> {
			   if(oldValue!=newValue){
				   newDay.getItems().clear();
			   }
			   if(!newDay.isDisable()){
				   Calendar time=Calendar.getInstance(); 
				   time.clear(); 
				   time.set(Calendar.YEAR,newYear.getValue()); 
				   //year年
				   time.set(Calendar.MONTH,newMonth.getValue()-1);
				   //Calendar对象默认一月为0,month月            
				   int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
				   for(int i=1;i<=day;i++){
					   newDay.getItems().add(i);
				   }
			   }
			   newDay.setValue(1);
			});

		grid.add(new Label("生日:"), 0, 0);
		grid.add(newYear, 1, 0);
		grid.add(new Label("年"), 2, 0);
		grid.add(newMonth, 3, 0);
		grid.add(new Label("月"), 4, 0);
		grid.add(newDay, 5, 0);
		grid.add(new Label("日"), 6, 0);

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node confirmButton = dialog1.getDialogPane().lookupButton(ConfirmButtonType);
		confirmButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		newYear.valueProperty().addListener((observable, oldValue, newValue) -> {
			confirmButton.setDisable(newValue == null);
		});

		dialog1.getDialogPane().setContent(grid);

		dialog1.setResultConverter(dialogButton -> {
			if (dialogButton == ConfirmButtonType) {
				return LocalDate.of(newYear.getValue(), newMonth.getValue(), newDay.getValue());
			}
			return null;
		});

		Optional<LocalDate> result = dialog1.showAndWait();

		result.ifPresent(newInfo -> {
			MemberManageInfoAccessorImpl.getInstance().setMemberVo(vo);
			MemberManageInfoAccessorImpl.getInstance().setBirthday(newInfo);
			ManageMemberCourier.getInstance().ModifyMemberInfo();
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改成功", "已成功修改客户信息");
			alert.showDialog();
		});
	}

	private void initBusinessMemberDialog() {
		// Create the custom dialog.
		dialog2 = new Dialog<>();
		dialog2.setTitle("修改客户信息");
		dialog2.setHeaderText("填写修改的信息");

		ConfirmButtonType = new ButtonType("确认", ButtonData.OK_DONE);
		dialog2.getDialogPane().getButtonTypes().addAll(ConfirmButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		TextField newEnt = new TextField();
		newEnt.setText(vo.getEnterprise());

		grid.add(new Label("企业:"), 0, 0);
		grid.add(newEnt, 1, 0);

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node confirmButton = dialog2.getDialogPane().lookupButton(ConfirmButtonType);
		confirmButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		newEnt.textProperty().addListener((observable, oldValue, newValue) -> {
			confirmButton.setDisable(newValue.trim().isEmpty());
		});

		dialog2.getDialogPane().setContent(grid);

		dialog2.setResultConverter(dialogButton -> {
			if (dialogButton == ConfirmButtonType) {
				return newEnt.getText();
			}
			return null;
		});

		Optional<String> result = dialog2.showAndWait();

		result.ifPresent(newInfo -> {
			MemberManageInfoAccessorImpl.getInstance().setMemberVo(vo);
			MemberManageInfoAccessorImpl.getInstance().setEnterprise(newEnt.getText());
			ManageMemberCourier.getInstance().ModifyMemberInfo();
			controller.handleQuery();
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改成功", "已成功修改客户信息");
			alert.showDialog();
		});
	}

}
