package presentation.hotel.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import presentation.hotel.model.OrderModel;
import presentation.hotel.view.abnormalOrder.fxml.AbnormalOrderController;
import vo.order.OrderVo;

public class AppealCell extends TableCell<OrderModel, OrderVo> {

	private Button cellButton;
	
	private OrderVo vo;

	private AbnormalOrderController controller;

	public AppealCell(AbnormalOrderController controller) {
		this.controller = controller;
		createButton();
	}

	private void createButton() {

		cellButton = new Button();
		cellButton.setText("延时");
		cellButton.setStyle(
				"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				if (vo != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("确认信息");
					alert.setHeaderText("请进行确认");
					alert.setContentText("确定执行该订单吗？");

					if (alert.showAndWait().get() == ButtonType.OK) {
						controller.delayOrder(vo);
					}
				}
			}
		});

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
			setGraphic(cellButton);
		} 
	}

}
