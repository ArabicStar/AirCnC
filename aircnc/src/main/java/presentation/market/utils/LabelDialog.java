package presentation.market.utils;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import presentation.market.model.MyOrderModel;

public class LabelDialog extends GridPane{
	Dialog<String> dialog;
//	Alert dialog;
	
	public LabelDialog(String content) {
//		dialog = new Dialog<String>();
		
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.setHeaderText(content);
	}
	
	public LabelDialog(String content, MyOrderModel orderModel) {
		// Create the custom dialog.
		dialog = new Dialog<String>();
		dialog.setTitle("                                                                            ");
		ButtonType btn = ButtonType.OK;
		
		dialog.getDialogPane().getButtonTypes().add(btn);
//		dialog.getDialogPane().getButtonTypes().remove(btn);
		dialog.initStyle(StageStyle.TRANSPARENT);
		dialog.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
		
		final int numberOfDetails = 11; 
		
		
		String[] labelNames = {
				"用户名",
				"酒店名",
				"订单号",
				"状态",
				"入住时间",
				"退房时间",
				"房间类型",
				"房间数量",
				"入住人数",
				"有无儿童",
				"总价"
		};
		String fontOfName = "-fx-text-fill: #969696; -fx-font-size: 16pt;";
		Label[] labels = new Label[numberOfDetails];
		for(int i = 0; i < numberOfDetails; i++) {
			labels[i] = new Label();
			labels[i].setText(labelNames[i]);
			labels[i].setStyle(fontOfName);
		}
		
		String[] labelProperties = {
				orderModel.getOrderVo().getUserName(),
				orderModel.getHotelName(),
				orderModel.getOrderVo().getOrderId(),
				orderModel.getState() + " (" + orderModel.isReviewed() + ")",
				orderModel.getCheckInTime(),
				orderModel.getLeaveTime(),
				orderModel.getOrderVo().getRoomType(),
				String.valueOf(orderModel.getOrderVo().getRoomNumber()),
				String.valueOf(orderModel.getOrderVo().getPeopleNumber()),
				orderModel.hasChildren(),
				orderModel.getTotalPrice()
		};
		String fontOfProperties = "-fx-text-fill: #585993; -fx-font-size: 16pt;";
		Label[] properties = new Label[numberOfDetails];
		for(int i = 0; i < numberOfDetails; i++) {
			properties[i] = new Label();
			properties[i].setText(labelProperties[i]);
			properties[i].setStyle(fontOfProperties);
		}

		// Set the button types.
	
		
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 200, 50, 10));

		for(int i = 0; i < numberOfDetails; i++) {
			grid.add(labels[i], 1, 1 + i);
			grid.add(properties[i], 2, 1 + i);
		}
//		Label cancelButton = new Label();
//		cancelButton.setText("");
//		cancelButton.setScaleX(10);
//		cancelButton.setScaleY(10);
//		DropShadow shadow = new DropShadow();
//		cancelButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) ->{
//			cancelButton.setEffect(shadow);
//		});
//		cancelButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
//			cancelButton.setEffect(null);
//		});
//		cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//			dialog.hide();
//		});
//		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent e){
//				System.out.println(e);
//				dialog.close();
//        	}
//		});
//		grid.add(cancelButton, 5, 1);
		grid.requestFocus();
		dialog.getDialogPane().setContent(grid);

	}

	public void showDialog() {
		dialog.showAndWait();
	}
}
