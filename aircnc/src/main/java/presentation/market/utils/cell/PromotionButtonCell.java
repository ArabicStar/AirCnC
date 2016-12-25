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
import presentation.market.model.WebsitePromotionModel;
import presentation.market.view.websitePromotion.fxml.WebsitePromotionController;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;
import vo.promotion.WebsitePromotionVo;

public class PromotionButtonCell extends TableCell<WebsitePromotionModel, PromotionVo> {
	private Button[] cellButton;
	private HBox buttons;
	private PromotionVo vo;
	private boolean practical = true;

	private WebsitePromotionController controller;

	public PromotionButtonCell(WebsitePromotionController controller) {
		createAllButtons();
		setController(controller);
	}

	public void setController(WebsitePromotionController controller) {
		this.controller = controller;
	}

	public void createAllButtons() {
		buttons = new HBox();
		buttons.setAlignment(Pos.CENTER_RIGHT);

		if (practical) {
			cellButton = new Button[] { createButtons(ButtonName.RECALL) };
		} else {
			cellButton = new Button[] { createButtons(ButtonName.SEND), createButtons(ButtonName.UPDATE),
					createButtons(ButtonName.DELETE) };
		}

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
	private Button createButtons(ButtonName type) {
		Button newButton = new Button();
		if (type == ButtonName.UPDATE) {
			newButton.setText("修改");
			newButton.setStyle(
					"-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else if (type == ButtonName.SEND) {
			newButton.setText("发布");
			newButton.setStyle(
					"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else if (type == ButtonName.RECALL) {
			newButton.setText("撤回");
			newButton.setStyle(
					"-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		} else if (type == ButtonName.DELETE) {
			newButton.setText("删除");
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
				case UPDATE:
					if (vo != null && !vo.getPractical()) {
						controller.addDetailPane(vo);
					}
					break;
				case DELETE:
					if (vo != null && !vo.getPractical()) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定删除该促销策略吗？");

						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.deletePromotion((WebsitePromotionVo) vo);
						}				
					}
					

					break;
				case RECALL:
					if (vo != null && vo.getPractical()) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定撤回该促销策略吗？");

						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.addAndUpdate(new PromotionVoBuilder(vo).setPractical(false));
						}				
						
					}

					break;
				case SEND:
					if (vo != null && !vo.getPractical()) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("确认信息");
						alert.setHeaderText("请进行确认");
						alert.setContentText("确定撤回该促销策略吗？");

						if (alert.showAndWait().get() == ButtonType.OK) {
							controller.addAndUpdate(new PromotionVoBuilder(vo).setPractical(true));
						}	
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
	protected void updateItem(PromotionVo vo, boolean empty) {
		super.updateItem(vo, empty);
		if (!empty) {
			this.vo = vo;
			this.practical = vo.getPractical();
			createAllButtons();
			setGraphic(buttons);
		}
	}
}
