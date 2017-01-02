package presentation.member.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.member.model.MyOrderModel;
import presentation.member.model.SearchHotelsModel;
import presentation.member.utils.dialog.LabelDialog;
import presentation.member.view.browsehotel.fxml.BrowseHotelsController;
import presentation.member.view.myorder.fxml.MemberOrderMainController;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.order.OrderVo;

public class BrowseCell extends TableCell<SearchHotelsModel, HotelVo> {
	private Button[] cellButton;
	private HBox buttons;
	private HotelVo hotel;
	private BrowseHotelsController controller;

	public BrowseCell(BrowseHotelsController controller) {
		createFunctionButtons();
		setController(controller);
	}

	public void setController(BrowseHotelsController controller) {
		this.controller = controller;
	}

	public void createFunctionButtons() {
		buttons = new HBox();

		cellButton = new Button[] { createButtons(FunctionButtonType.CHECK), createButtons(FunctionButtonType.REVERSE) };

		for (Button b : cellButton) {
			buttons.getChildren().add(b);
		}

		buttons.setStyle("-fx-spacing:3px; -fx-padding: 5 0 0 0;");
	}

	/**
	 * create different buttons : check, appeal, cancel, review
	 * 
	 * @param type
	 * @return
	 */
	private Button createButtons(FunctionButtonType type) {
		Button newButton = new Button();
		if (type == FunctionButtonType.CHECK) {
			newButton.setText("查看");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else if (type == FunctionButtonType.APPEAL) {
			newButton.setText("预定");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else {
			newButton.setText("");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		}

		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				switch (type) {
				case CHECK:
					if (hotel != null) {
						
					}
					break;
				case REVERSE:
					
					break;
				default:
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
			this.hotel = vo;
			createFunctionButtons();
			setGraphic(buttons);
		} else {
			setGraphic(null);
		}
	}
}
