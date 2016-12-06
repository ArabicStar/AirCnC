package presentation.market.utils;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import presentation.market.model.PromotionModel;

public class PromotionButtonCell extends TableCell<PromotionModel, ButtonName> {
	final Button cellButton;
	final ButtonName name;
	
	public PromotionButtonCell(ButtonName name) {
		cellButton = new Button();
		cellButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
		this.name = name;
		createButton();
	}
	
    /**
     * Display button if the row is not empty
     * @param status, empty
     */
    @Override
    protected void updateItem(ButtonName name, boolean empty) {
        super.updateItem(name, empty);
        if(!empty){
        	setGraphic(cellButton);
        }else{
        	setGraphic(null);
        }
    }
    
    public void createButton() {
    	switch (name) {
		case EDIT:
			cellButton.setText("编辑");
			cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
               	 Alert alert = new Alert(AlertType.INFORMATION);
               	 alert.setTitle("编辑促销策略");
               	 alert.setHeaderText("请对促销策略进行编辑");
               	 alert.setContentText("确定编辑该策略吗？");
               	 
               	 Optional<ButtonType> result = alert.showAndWait();
               	 if (result.get() == ButtonType.OK){
               	     // 
               	 } else {
               	     // 
               	 }
                }
            });
			break;

		default:
			break;
		}
    }
	

}
