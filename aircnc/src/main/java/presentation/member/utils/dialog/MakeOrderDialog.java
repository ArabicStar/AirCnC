package presentation.member.utils.dialog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import presentation.member.accessor.OrderMakerAccessor;
import presentation.member.accessor.impl.OrderMakerAccessorImpl;
import presentation.member.model.MyOrderModel;
import presentation.member.utils.DateTimePicker;
import utils.info.hotel.Room;
import vo.hotel.HotelVo;

public class MakeOrderDialog {
	Dialog<String> dialog;
	private OrderMakerAccessor accessor;

	public MakeOrderDialog(String content, HotelVo hotelVo) {
		if(!OrderMakerAccessorImpl.isLaunch()) {
			accessor = OrderMakerAccessorImpl.launch();
		} else {
			accessor = OrderMakerAccessorImpl.getIntance();
		}
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
		
		
		final DatePicker enterDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(1));
		enterDatePickeratePicker.setShowWeekNumbers(true);
		final Callback<DatePicker, DateCell> enterDayCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						
						if(item.isBefore(LocalDate.now().plusDays(1))){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		enterDatePickeratePicker.setDayCellFactory(enterDayCellFactory);
		
		final DatePicker leaveDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(2));
		leaveDatePickeratePicker.setShowWeekNumbers(true);
		final Callback<DatePicker, DateCell> leaveDayCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
//						datePicker.setValue(enterDatePickeratePicker.getValue().plusDays(1));
						if(item.isBefore(enterDatePickeratePicker.getValue().plusDays(1))){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		leaveDatePickeratePicker.setDayCellFactory(leaveDayCellFactory);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20, 150, 10, 10));
		dialog.getDialogPane().setContent(gridPane);
		
		String enterDate = "入住时间";
		Label enterDateLabel = new Label(enterDate);
		String fontOfName = "-fx-text-fill: #5c5d93; -fx-font-size: 16pt;";
		enterDateLabel.setStyle(fontOfName);
		gridPane.add(enterDateLabel, 1, 1);
		gridPane.add(enterDatePickeratePicker, 2, 1);
		
		
		String leaveDate = "退房时间";
		Label leaveDateLabel = new Label(leaveDate);
		leaveDateLabel.setStyle(fontOfName);
		gridPane.add(leaveDateLabel, 1, 2);
		gridPane.add(leaveDatePickeratePicker, 2, 2);
		
		String roomType = "房间类型";
		Label roomTypeLabel = new Label(roomType);
		roomTypeLabel.setStyle(fontOfName);

		ObservableList<String> options = FXCollections.observableArrayList(); 
		Room[] tempRooms = (Room[])hotelVo.getRooms().toArray();
		for(int i = 0; i < tempRooms.length; i++) {
			options.add(tempRooms[i].getName());
		}
		// 这里应该是room有问题导致无法运行，因此使用替换策略
//		ObservableList<String> options = FXCollections.observableArrayList(
//				"单人间", "双人间", "三人间");
		ComboBox<String> roomTypeBox = new ComboBox<>(options);
		roomTypeBox.setValue(options.get(0));
		gridPane.add(roomTypeLabel, 1, 3);
		gridPane.add(roomTypeBox, 2, 3);
		
		
		
		String roomNumber = "房间数量";
		Label roomNumberLabel = new Label(roomNumber);
		roomNumberLabel.setStyle(fontOfName);
		TextField roomNumberField = new TextField();
		roomNumberField.setText("1");
		roomNumberField.setMaxWidth(80);
		gridPane.add(roomNumberLabel, 1, 4);
		gridPane.add(roomNumberField, 2, 4);
		
		String latestExecuteTime = "最晚订单执行时间";
		Label latestExecuteTimeLabel = new Label(latestExecuteTime);
		latestExecuteTimeLabel.setStyle(fontOfName);
		DateTimePicker timePicker = new DateTimePicker();
		LocalDateTime tempLastestDateTime = LocalDateTime.now();
		LocalTime time = tempLastestDateTime.toLocalTime();
		tempLastestDateTime = tempLastestDateTime.minusMinutes(time.getMinute()).plusDays(1);
		timePicker.setDateTimeValue(tempLastestDateTime);
		gridPane.add(latestExecuteTimeLabel, 1, 5);
		gridPane.add(timePicker, 2, 5);
		final Callback<DatePicker, DateCell> lastestEcecuteCellFactory = new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						
//						datePicker.setValue(enterDatePickeratePicker.getValue());
						LocalDateTime dateTimeValue;
						LocalTime tempTime = LocalTime.now();
						int minutes = tempTime.getMinute();
						tempTime = tempTime.minusMinutes(minutes);
						dateTimeValue = LocalDateTime.of(enterDatePickeratePicker.getValue(), tempTime);
						timePicker.setDateTimeValue(dateTimeValue);
						if(!item.equals(enterDatePickeratePicker.getValue())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb");
						}
					}
				};
			}
		};
		timePicker.setDayCellFactory(lastestEcecuteCellFactory);
		
		String peopleNumber = "入住人数";
		Label peopleNumberLabel = new Label(peopleNumber);
		peopleNumberLabel.setStyle(fontOfName);
		TextField peopleNumberField = new TextField();
		peopleNumberField.setText("1");
		peopleNumberField.setMaxWidth(80);
		gridPane.add(peopleNumberLabel, 1, 6);
		gridPane.add(peopleNumberField, 2, 6);
		
		String hasChildren = "有无儿童";
		Label hasChildrenLabel = new Label(hasChildren);
		hasChildrenLabel.setStyle(fontOfName);
		final ToggleGroup group = new ToggleGroup(); 
		RadioButton rb1 = new RadioButton("有");  
		rb1.setToggleGroup(group);  
		rb1.setSelected(true);  
		RadioButton rb2 = new RadioButton("无");  
		rb2.setToggleGroup(group);
		gridPane.add(hasChildrenLabel, 1, 7);
		gridPane.add(rb2, 2, 7);
		gridPane.add(rb1, 3, 7);
		
		ButtonType btn = new ButtonType("确认", ButtonData.APPLY);
		dialog.getDialogPane().getButtonTypes().add(btn);
		Node ensureButton = dialog.getDialogPane().lookupButton(btn);
		
		ensureButton.setStyle(
				"-fx-background-color: #5c5d93;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		ensureButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				// hasChildren
				if(rb1.getToggleGroup().getSelectedToggle()!=null) {
					String temp = rb1.getToggleGroup().getSelectedToggle().toString();
					String result = temp.substring(temp.length() - 2, temp.length() - 1);
					if(result.equals("有")) {
						accessor.setHasChildren(true);
					} else {
						accessor.setHasChildren(false);
					}
				}
				
				// enterDate and leaveDate and latestOrderExecuteTime
				Alert enterTimeAlert;
				if(!enterDatePickeratePicker.getValue().isAfter(LocalDate.now())) {
					enterTimeAlert = new Alert(AlertType.ERROR);
					enterTimeAlert.setContentText("入住时间 最早只能预订第二天的房间！");
					enterTimeAlert.show();
				}
				Alert leaveTimeAlert;
				if(!leaveDatePickeratePicker.getValue().isAfter(enterDatePickeratePicker.getValue())) {
					leaveTimeAlert = new Alert(AlertType.ERROR);
					leaveTimeAlert.setContentText("离开时间 必须迟于入住时间！");
					leaveTimeAlert.show();
				}
				Alert latestTimeAlert;
				LocalDate lastestTime = timePicker.getValue();
				if(!lastestTime.isEqual(enterDatePickeratePicker.getValue())) {
					latestTimeAlert = new Alert(AlertType.ERROR);
					latestTimeAlert.setContentText("最晚订单执行时间 必须与入住时间在同一天！");
					latestTimeAlert.show();
				}
				
				accessor.setEnterTime(enterDatePickeratePicker.getValue());
				accessor.setLeaveTime(leaveDatePickeratePicker.getValue());
				accessor.setLatestExecuteTime(timePicker.getDateTimeValue());
				
				
				// peopleNumber
				
				Alert peopleNumAlertlert = new Alert(AlertType.ERROR);
				peopleNumAlertlert.setContentText("入住人数 请输入正整数(阿拉伯数字)！");
				try {
					int peopleNum = Integer.valueOf(peopleNumberField.getText().trim());
					accessor.setPeopleNumber(peopleNum);
					if(peopleNum <= 0) {
						peopleNumAlertlert.show();
					}
				} catch (Exception e) {
					
					peopleNumAlertlert.show();
				}
				
				// roomNumber
				Alert roomNumAlert = new Alert(AlertType.ERROR);
				roomNumAlert.setContentText("房间数量 请输入正整数(阿拉伯数字)！");
				try {
					int roomNum = Integer.valueOf(roomNumberField.getText().trim());
					accessor.setRoomNumber(roomNum);
					if(roomNum < 0) {
						roomNumAlert.show();
					}
				} catch (Exception e) {
					roomNumAlert.show();
				}
				
				accessor.setRoomType(roomTypeBox.getValue());
			}
			
		});
		
		ensureButton.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				// TODO:显示订单的信息
				// 拿到折扣信息，并进行优惠
				String temp = rb1.getToggleGroup().getSelectedToggle().toString();
				String result = temp.substring(temp.length() - 2, temp.length() - 1);
				boolean hasChildren = result.equals("有") ? true : false;
				String detail = enterDateLabel.getText() + " " + enterDatePickeratePicker.getValue().toString() + "\r\n"
						+ leaveDateLabel.getText() + " " + leaveDatePickeratePicker.getValue().toString() + "\r\n"
						+ roomTypeLabel.getText() + " " + roomTypeBox.getValue().toString() + "\r\n"
						+ roomNumberLabel.getText() + " " + roomNumberField.getText() + "\r\n"
						+ latestExecuteTimeLabel.getText() + " " + enterDatePickeratePicker.getValue().toString() + " " + timePicker.getDateTimeValue().getHour() + ":00" + "\r\n"
						+ peopleNumberLabel.getText() + " " + peopleNumberField.getText() + "\r\n"
						+ hasChildrenLabel.getText() + " " + result + "\r\n";
				Node yesButton = alert.getDialogPane().lookupButton(ButtonType.OK);
				yesButton.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						
					}
				});
			}
		});
//		ensureButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				System.out.println("rweqtqwyq34y6q34643	5346t3q");
//			}
//		
//		});
	}
	

	public MakeOrderDialog(String content, MyOrderModel orderModel) {
		// Create the custom dialog.
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Label orderDetail = new Label();
		

		grid.add(orderDetail, 8, 5);
//		orderDetail.setEditable(false);
		String detail = 
				"用户名 " + orderModel.getUserName() + "\n" 
		+ "酒店名 " + orderModel.getHotelName() + "\n"
		+ "订单号 " + orderModel.getOrderID() + "\n"
		+ "状态" + orderModel.getState()
		+ "入住时间 " + orderModel.getCheckInTime() + "\n"
		+ "退房时间 " + orderModel.getLeaveTime() + "\n"
		+ "房间类型 " + orderModel.getRoomType() + "\n"
		+ "房间数量 " + orderModel.getRoomNumber() + "\n"
		+ "入住人数 " + orderModel.getPeopleNumber() + "\n"
		+ "有无儿童 " + orderModel.hasChild() + "\n"
		+ "总价 " + orderModel.getTotalPrice() + "\n"
		;
		orderDetail.setText(detail);


		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> orderDetail.requestFocus());


	}

	public void showDialog() {
		dialog.showAndWait();
	}
}
