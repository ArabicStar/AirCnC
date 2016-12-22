package presentation.manage.utils.dialog;

import java.time.LocalDate;
import java.util.Optional;

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
import presentation.member.utils.dialog.PlainDialog;
import vo.member.MemberVo;

public class MemberModifyDialog {

	private Dialog<LocalDate> dialog1;
	private Dialog<String> dialog2;
	ButtonType ConfirmButtonType;
	private GridPane grid;
	private MemberVo vo;

	public MemberModifyDialog(MemberVo vo) {
		this.vo = vo;
		if (vo.getType().toUpperCase().equals("BUSINESS"))
			initBusinessMemberDialog();
		else
			initPersonMemberDialog();

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

		ComboBox<Integer> newMonth = new ComboBox<Integer>();
		newMonth.setValue(vo.getBirthday().getMonthValue() + 1);

		ComboBox<Integer> newDay = new ComboBox<Integer>();
		newDay.setValue(vo.getBirthday().getDayOfMonth());

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
			// MemberInfoCourier.getInstance().updatePassword();
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
			// MemberInfoCourier.getInstance().updatePassword();
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "修改成功", "已成功修改客户信息");
			alert.showDialog();
		});
	}

}
