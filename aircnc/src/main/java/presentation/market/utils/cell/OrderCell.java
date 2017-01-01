package presentation.market.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.market.model.OrderModel;
import presentation.market.utils.dialog.OrderDialog;
import presentation.market.view.abnormalorderbrowse.fxml.AbnormalOrderBrowseController;
import vo.order.OrderVo;

public class OrderCell extends TableCell<OrderModel, OrderVo>{
	private Button[] cellButton;
	private HBox buttons;
	private OrderVo vo;
	private AbnormalOrderBrowseController controller;

	public OrderCell(AbnormalOrderBrowseController controller) {
		this.controller = controller;
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

		cellButton = new Button[] { createButton(ButtonName.CHECK), createButton(ButtonName.APPEAL) };

		for (Button b : cellButton) {
			buttons.getChildren().add(b);
		}

		buttons.setStyle("-fx-spacing:3px; -fx-padding: 5 0 0 0;");

	}

	private Button createButton(ButtonName type) {
		Button newButton = new Button();

		if (type == ButtonName.APPEAL) {
			newButton.setText("通过");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else {
			newButton.setText("查看");
			newButton.setStyle(
					"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		}
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				switch (type) {
				case APPEAL:
					if (vo != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定通过该订单吗？");

						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.approveOrder(vo);
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
			createAllButtons();
			setGraphic(buttons);
		}else{
			setGraphic(null);
		}
	}
	
}
