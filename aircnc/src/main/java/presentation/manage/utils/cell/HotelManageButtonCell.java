package presentation.manage.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.manage.model.HotelManageModel;
import presentation.manage.view.hotelmanage.fxml.HotelManageMainController;
import vo.hotel.HotelVo;

/**
 * FunctionButtons use a hbox(button) to show the operations.
 * 
 * @author paranoia
 *
 */
public class HotelManageButtonCell extends TableCell<HotelManageModel, HotelVo> {

	private Button[] cellButton;
	private HBox buttons;
	@SuppressWarnings("unused")
	private HotelVo vo;
	private HotelManageMainController controller;

	public HotelManageButtonCell(HotelManageMainController controller) {
		this.controller = controller;
		createButtons();
	}

	public void createButtons() {
		buttons = new HBox();
		cellButton = new Button[] { createButtons(ButtonType.CHECK), createButtons(ButtonType.MODIFY) , createButtons(ButtonType.DELETE)};
		for (Button b : cellButton) {
			buttons.getChildren().add(b);
		}

		buttons.setStyle("-fx-spacing:3px; -fx-padding: 5 0 0 0;");
		buttons.setAlignment(Pos.CENTER);
	}

	/**
	 * create different buttons : check, appeal, cancel, review
	 * 
	 * @param type
	 * @return
	 */
	private Button createButtons(ButtonType type) {
		Button newButton = new Button();
		switch (type) {
		case CHECK:
			newButton.setText("查看");
			newButton.setStyle(
					"-fx-background-color: #4f4e84;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
			break;
		case MODIFY:
			newButton.setText("修改");
			newButton.setStyle(
					"-fx-background-color: #4f4e84;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
			break;
		case DELETE:
			newButton.setText("删除");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
			break;
		default:

		}

		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				switch (type) {
				case CHECK:
					controller.handleDetailedInfo();
					break;
				case MODIFY:
					break;
				case DELETE:
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
	protected void updateItem(HotelVo vo, boolean empty) {
		super.updateItem(vo, empty);
		if (!empty) {
			this.vo = vo;
			createButtons();
			setGraphic(buttons);
		}
	}

}
