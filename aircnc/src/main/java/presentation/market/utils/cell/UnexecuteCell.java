package presentation.market.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.market.model.OrderModel;
import presentation.market.utils.dialog.UnexecuteOrderDialog;
import vo.order.OrderVo;

public class UnexecuteCell extends TableCell<OrderModel, OrderVo> {
	private Button[] cellButton;
	private HBox buttons;
	private OrderVo vo;
//	private UnexecuteOrderController controller;

	public UnexecuteCell() {
//		this.controller = controller;
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
		buttons.setAlignment(Pos.CENTER);

		cellButton = new Button[] { createButton(ButtonName.CHECK) };

		for (Button b : cellButton) {
			buttons.getChildren().add(b);
		}

		buttons.setStyle("-fx-spacing:3px; -fx-padding: 5 0 0 0;");

	}

	private Button createButton(ButtonName type) {
		Button newButton = new Button();

		newButton.setText("查看");
		newButton.setStyle(
				"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				if (vo != null) {
					UnexecuteOrderDialog details = new UnexecuteOrderDialog(new OrderModel(vo));
					details.showDialog();
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
		} else {
			setGraphic(null);
		}
	}
}
