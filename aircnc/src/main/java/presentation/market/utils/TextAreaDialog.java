package presentation.market.utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import presentation.market.model.MyOrderModel;
import vo.hotel.HotelVo;

public class TextAreaDialog extends GridPane {
	Dialog<String> dialog;

	public TextAreaDialog(String content) {
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
	}
	
	public TextAreaDialog(String content, HotelVo hotel) {
		dialog = new Dialog<String>();
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
		
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextArea orderDetail = new TextArea();
		grid.add(orderDetail, 8, 5);
		orderDetail.setEditable(false);
		String detail = 
				  "装备: " + hotel.getEquipment() + "\n"
				+ hotel.getGrade() + "\n"
				+ hotel.getId() + "\n"
				+ hotel.getIntroduction() + "\n"
				;
		orderDetail.setText(detail);
		
		dialog.getDialogPane().setContent(grid);
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
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
		
		
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextArea orderDetail = new TextArea();
		

		grid.add(orderDetail, 8, 5);
		orderDetail.setEditable(false);
		String detail = 
				"用户名 " + orderModel.getOrderVo().getUserName() + "\n" 
		+ "酒店名 " + orderModel.getHotelName() + "\n"
		+ "订单号 " + orderModel.getOrderVo().getOrderId() + "\n"
		+ "状态" + orderModel.getState() + " (" + orderModel.isReviewed() + ")\n" 
		+ "入住时间 " + orderModel.getCheckInTime() + "\n"
		+ "退房时间 " + orderModel.getLeaveTime() + "\n"
		+ "房间类型 " + orderModel.getOrderVo().getRoomType() + "\n"
		+ "房间数量 " + orderModel.getOrderVo().getRoomNumber() + "\n"
		+ "入住人数 " + orderModel.getOrderVo().getPeopleNumber() + "\n"
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
