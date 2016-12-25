package presentation.hotel.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.hotel.model.OrderModel;
import presentation.hotel.utils.dialog.OrderDialog;
import presentation.hotel.view.orderBrowse.fxml.OrderBrowseController;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class OrderButtonCell extends TableCell<OrderModel, OrderVo> {
	private Button[] cellButton;
	private HBox buttons;
	private OrderVo vo;
	private OrderBrowseController controller;
	private OrderStatus status;

	public OrderButtonCell(OrderBrowseController controller) {
		this.controller = controller;
		status = OrderStatus.UNEXECUTED;
		createAllButtons();
	}

	/**
	 * create different buttons : check, appeal, cancel, review
	 * 
	 * @param type
	 * @return
	 */
	private void createAllButtons() {
		buttons = new HBox();
		buttons.setAlignment(Pos.CENTER_RIGHT);

		switch (status) {
		case UNEXECUTED:
			cellButton = new Button[] { createButton(ButtonName.EXECUTE), createButton(ButtonName.CHECK) };
			break;

		case ABNORMAL:
			cellButton = new Button[] { createButton(ButtonName.DELAY), createButton(ButtonName.CHECK) };
			break;

		default:
			cellButton = new Button[] { createButton(ButtonName.CHECK) };

		}

		for (Button b : cellButton) {
			buttons.getChildren().add(b);
		}

		buttons.setStyle("-fx-spacing:3px; -fx-padding: 5 0 0 0;");

	}

	private Button createButton(ButtonName type) {
		Button newButton = new Button();

		if (type == ButtonName.EXECUTE) {
			newButton.setText("执行");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else if (type == ButtonName.DELAY) {
			newButton.setText("延时");
			newButton.setStyle(
					"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else {
			newButton.setText("查看");
			newButton.setStyle(
					"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		}
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				switch (type) {
				case EXECUTE:
					if (vo != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定执行该订单吗？");

						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.executeOrder(vo);
						}
					}
					break;
				case DELAY:
					if (vo != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定执行该订单吗？");
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定执行该订单吗？");
						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.delayOrder(vo);
						}
					}
					break;
				case CHECK:
					if (vo != null) {
						OrderDialog details = new OrderDialog(new OrderModel(vo));
						details.showDialog();
					}

					break;
				}
			}
		});

		return newButton;
	}

	/**
	 * Display button if the row is not empty
	 * 
	 * @param status,
	 *            empty
	 */
	@Override
	protected void updateItem(OrderVo vo, boolean empty) {
		super.updateItem(vo, empty);
		if (!empty) {
			this.vo = vo;
			this.status = vo.getStatus();
			createAllButtons();
			setGraphic(buttons);
		}
	}
}
