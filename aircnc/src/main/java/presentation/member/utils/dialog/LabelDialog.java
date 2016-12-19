package presentation.member.utils.dialog;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import presentation.member.model.MyOrderModel;

public class LabelDialog extends GridPane {
	Dialog<String> dialog;
	// Alert dialog;

	public LabelDialog() {

		dialog.initStyle(StageStyle.UNDECORATED);
	}

	public LabelDialog(MyOrderModel orderModel) {
		// Create the custom dialog.
		dialog = new Dialog<String>();
		ButtonType btn = ButtonType.OK;
		dialog.getDialogPane().getButtonTypes().add(btn);
		Node ensureButton = dialog.getDialogPane().lookupButton(btn);
		ensureButton.setStyle(
				"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		dialog.initStyle(StageStyle.UNDECORATED);
		dialog.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
		dialog.getDialogPane().setEffect(new DropShadow());
		final int numberOfDetails = 11;

		String[] labelNames = { "用户名", "酒店名", "订单号", "状态", "入住时间", "退房时间", "房间类型", "房间数量", "入住人数", "有无儿童", "总价" };
		String fontOfName = "-fx-text-fill: #969696; -fx-font-size: 16pt;";
		Label[] labels = new Label[numberOfDetails];
		for (int i = 0; i < numberOfDetails; i++) {
			labels[i] = new Label();
			labels[i].setText(labelNames[i]);
			labels[i].setStyle(fontOfName);
		}

		String[] labelProperties = { orderModel.getUserName(), orderModel.getHotelName(),
				orderModel.getOrderID(), orderModel.getState(), 
				orderModel.getCheckInTime(), orderModel.getLeaveTime(), orderModel.getRoomType(),
				String.valueOf(orderModel.getRoomNumber()),
				String.valueOf(orderModel.getPeopleNumber()), orderModel.hasChild(),
				orderModel.getTotalPrice() };
		String fontOfProperties = "-fx-text-fill: #585993; -fx-font-size: 16pt;";
		Label[] properties = new Label[numberOfDetails];
		for (int i = 0; i < numberOfDetails; i++) {
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
		for (int i = 0; i < numberOfDetails; i++) {
			grid.add(labels[i], 1, 1 + i);
			grid.add(properties[i], 2, 1 + i);
		}
		grid.add(ensureButton, 3, 14);
		
		grid.requestFocus();
		dialog.getDialogPane().setContent(grid);

	}

	public void showDialog() {
		dialog.showAndWait();
	}
}

