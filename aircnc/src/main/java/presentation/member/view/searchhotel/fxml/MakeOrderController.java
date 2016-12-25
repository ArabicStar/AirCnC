package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.model.SearchHotelsModel;
import presentation.member.utils.DateTimePicker;
import presentation.member.utils.dialog.PlainDialog;
import presentation.member.view.searchhotel.MakeOrderNextPane;

public class MakeOrderController implements Initializable {

	@FXML
	private HBox checkInTimeHbox;

	@FXML
	private HBox checkOutTimeHbox;

	@FXML
	private HBox latestTimeHbox;

	@FXML
	private ComboBox<String> roomType;

	@FXML
	private TextField roomNum;

	@FXML
	private TextField peopleNum;

	@FXML
	private RadioButton hasChild;

	@FXML
	private RadioButton notHasChild;

	private ToggleGroup child;

	private MemberSearchHotelGeneralController controller;
	private SearchHotelsModel model;
	private AnchorPane rootLayout;
	private MakeOrderNextPane nextPane;

	private DateTimePicker timePicker;
	private DatePicker enterDatePickeratePicker;
	private DatePicker leaveDatePickeratePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enterDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(1));
		enterDatePickeratePicker.setShowWeekNumbers(true);
		final Callback<DatePicker, DateCell> enterDayCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(LocalDate.now().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		enterDatePickeratePicker.setDayCellFactory(enterDayCellFactory);
		enterDatePickeratePicker.setStyle(null);
		checkInTimeHbox.getChildren().add(enterDatePickeratePicker);

		leaveDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(2));
		leaveDatePickeratePicker.setShowWeekNumbers(true);
		final Callback<DatePicker, DateCell> leaveDayCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						// datePicker.setValue(enterDatePickeratePicker.getValue().plusDays(1));
						if (item.isBefore(enterDatePickeratePicker.getValue().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		leaveDatePickeratePicker.setDayCellFactory(leaveDayCellFactory);
		leaveDatePickeratePicker.setStyle(null);
		checkOutTimeHbox.getChildren().add(leaveDatePickeratePicker);

		timePicker = new DateTimePicker();

		final Callback<DatePicker, DateCell> lastestEcecuteCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						// datePicker.setValue(enterDatePickeratePicker.getValue());
						LocalDateTime dateTimeValue;
						LocalTime tempTime = LocalTime.now();
						int minutes = tempTime.getMinute();
						tempTime = tempTime.minusMinutes(minutes);
						dateTimeValue = LocalDateTime.of(enterDatePickeratePicker.getValue(), tempTime);
						timePicker.setDateTimeValue(dateTimeValue);
						if (!item.equals(enterDatePickeratePicker.getValue())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		timePicker.setDayCellFactory(lastestEcecuteCellFactory);
		timePicker.setStyle(null);
		latestTimeHbox.getChildren().add(timePicker);

//		String[] rooms = model.getRoomName().split("\n");
//		for (String s : rooms)
//			roomType.getItems().add(s);

		child = new ToggleGroup();
		hasChild.setToggleGroup(child);
		notHasChild.setToggleGroup(child);
		notHasChild.setSelected(true);

	}

	@FXML
	public void handleNext() {
		if (checkIntegerInput(peopleNum.getText()) && checkIntegerInput(roomNum.getText())) {
			LocalTime time = LocalTime.MIDNIGHT;
			LocalDateTime entry = LocalDateTime.of(enterDatePickeratePicker.getValue(), time);
			LocalDateTime leave = LocalDateTime.of(leaveDatePickeratePicker.getValue(), time);
			OrderMakerAccessorImpl.getIntance().setEnterTime(entry);
			OrderMakerAccessorImpl.getIntance().setLeaveTime(leave);
			OrderMakerAccessorImpl.getIntance().setRoomType(roomType.getValue());
			OrderMakerAccessorImpl.getIntance().setRoomNumber(Integer.valueOf(roomNum.getText()));
			OrderMakerAccessorImpl.getIntance().setPeopleNumber(Integer.valueOf(peopleNum.getText()));
			OrderMakerAccessorImpl.getIntance().setLatestExecuteTime(timePicker.getDateTimeValue());
			OrderMakerAccessorImpl.getIntance().setHasChildren(hasChild.isSelected());
			nextPane = new MakeOrderNextPane();
			rootLayout.getChildren().add(nextPane.getPane());
			AnchorPane.setTopAnchor(nextPane.getPane(), 0.0);
			nextPane.getController().setParentController(this.controller);
		} else {
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "下订单", "请输入有效信息！");
			alert.showDialog();
		}
	}

	public static boolean checkIntegerInput(String s) {
		try {
			Integer.valueOf(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@FXML
	public void handleClose() {
		controller.removeReverse();
	}

	public void setController(MemberSearchHotelGeneralController controller) {
		this.controller = controller;
	}

	public void setHotelVo(SearchHotelsModel model) {
		this.model = model;
	}

	public void setRootLayout(AnchorPane pane) {
		this.rootLayout = pane;
	}

}
