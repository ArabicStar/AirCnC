package presentation.market.utils;

import java.time.LocalDate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import presentation.market.accessor.MakeOrderAccessor;
import presentation.market.accessor.impl.MakeOrderAccessorImpl;
import presentation.market.model.MyOrderModel;
import vo.hotel.HotelVo;

public class TextAreaDialog extends GridPane {
	Dialog<String> dialog;
	private MakeOrderAccessor accessor;
	public TextAreaDialog(String content, HotelVo hotelVo) {
//		MakeOrderAccessorImpl.launch();
		accessor = MakeOrderAccessorImpl.getIntance();
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
		
		
		final DatePicker enterDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(1));
		enterDatePickeratePicker.setShowWeekNumbers(true);
		final DatePicker leaveDatePickeratePicker = new DatePicker(LocalDate.now().plusDays(2));
		leaveDatePickeratePicker.setShowWeekNumbers(true);

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
		// 实际情况
//		ObservableList<String> options = FXCollections.observableArrayList(); 
//		Room[] tempRooms = (Room[])hotelVo.getRooms().toArray();
//		for(int i = 0; i < tempRooms.length; i++) {
//			options.add(tempRooms[i].getName());
//		}
		// 这里应该是room有问题导致无法运行，因此使用替换策略
		ObservableList<String> options = FXCollections.observableArrayList(
				"单人间", "双人间", "三人间");
		ComboBox<String> roomTypeBox = new ComboBox<>(options);
		gridPane.add(roomTypeLabel, 1, 3);
		gridPane.add(roomTypeBox, 2, 3);
		
		
		
		String roomNumber = "房间数量";
		Label roomNumberLabel = new Label(roomNumber);
		roomNumberLabel.setStyle(fontOfName);
		TextField textField = new TextField();
		textField.setMaxWidth(80);
		gridPane.add(roomNumberLabel, 1, 4);
		gridPane.add(textField, 2, 4);
		
		String latestExecuteTime = "最晚订单执行时间";
		Label latestExecuteTimeLabel = new Label(latestExecuteTime);
		latestExecuteTimeLabel.setStyle(fontOfName);
		DateTimePicker timePicker = new DateTimePicker();
		gridPane.add(latestExecuteTimeLabel, 1, 5);
		gridPane.add(timePicker, 2, 5);
		
		String peopleNumber = "入住人数";
		Label peopleNumberLabel = new Label(peopleNumber);
		peopleNumberLabel.setStyle(fontOfName);
		TextField textField2 = new TextField();
		textField2.setMaxWidth(80);
		gridPane.add(peopleNumberLabel, 1, 6);
		gridPane.add(textField2, 2, 6);
		
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
		
		ButtonType btn = new ButtonType("提交", ButtonData.APPLY);
		dialog.getDialogPane().getButtonTypes().add(btn);
		Node ensureButton = dialog.getDialogPane().lookupButton(btn);
		
		ensureButton.setStyle(
				"-fx-background-color: #5c5d93;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		ensureButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(rb1.getToggleGroup().getSelectedToggle()!=null) {
					System.out.println("非空");
					String temp = rb1.getToggleGroup().getSelectedToggle().toString();
					String result = temp.substring(temp.length() - 2, temp.length() - 1);
					System.out.println(accessor == null);
					if(result.equals("有")) {
						accessor.setHasChildren(true);
					}
				} else {
					System.out.println("空");
				}
				
				System.out.println("11111111111111");
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
	

	public TextAreaDialog(String content, MyOrderModel orderModel) {
		// Create the custom dialog.
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new
		// ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
//		ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		
		// Create the username and password labels and fields.
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
		+ "订单号 " + orderModel.getOrderId() + "\n"
		+ "状态" + orderModel.getState() + " (" + orderModel.isReviewed() + ")\n" 
		+ "入住时间 " + orderModel.getCheckInTime() + "\n"
		+ "退房时间 " + orderModel.getLeaveTime() + "\n"
		+ "房间类型 " + orderModel.getRoomType() + "\n"
		+ "房间数量 " + orderModel.getRoomNumber() + "\n"
		+ "入住人数 " + orderModel.getPeopleNumber() + "\n"
		+ "有无儿童 " + orderModel.hasChildren() + "\n"
		+ "总价 " + orderModel.getTotalPrice() + "\n"
		;
		orderDetail.setText(detail);

		// Enable/Disable login button depending on whether a username was
		// entered.
//		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
//		orderDetail.textProperty().addListener((observable, oldValue, newValue) -> {
//			loginButton.setDisable(newValue.trim().isEmpty());
//		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> orderDetail.requestFocus());

		// Convert the result to a username-password-pair when the login button
		// is clicked.
//		dialog.setResultConverter(dialogButton -> {
//			if (dialogButton == loginButtonType) {
//				return new String(orderDetail.getText());
//			}
//			return null;
//		});

//		Optional<String> result = dialog.showAndWait();
//
//		result.ifPresent(usernamePassword -> {
//			System.out.println("Username=" + usernamePassword.toString());
//		});
		

	}

	public void showDialog() {
		dialog.showAndWait();
	}
}
