package presentation.market.utils;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.Case;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import presentation.market.model.OrderModel;

public class ButtonCell extends TableCell<OrderModel, ButtonName>{
	
	final Button cellButton;
	final ButtonName name;
	
    
    public ButtonCell(ButtonName name){
    	cellButton = new Button();
    	cellButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	this.name = name;
    	createButton();  	
    }
    
    public void initial(){
    	this.getTableView().getItems().remove(this.getIndex());
    	this.updateItem(name, true);
    }
    
   /**
    * create different buttons : check, appeal, cancel, review
    * 
    * @param type
    * @return
    */
    private void createButton(){
    	switch(name){
    	case EXECUTE:
    		cellButton.setText("执行"); 
    		cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
               	 Alert alert = new Alert(AlertType.CONFIRMATION);
               	 alert.setTitle("确认信息");
               	 alert.setHeaderText("请进行确认");
               	 alert.setContentText("确定执行该订单吗？");
               	 
               	 Optional<ButtonType> result = alert.showAndWait();
               	 if (result.get() == ButtonType.OK){
               		 initial();
               	 } else {
               	     // 
               	 }
                }
            });
    		break;
    	case DELAY:
    		cellButton.setText("延时");
    		cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
               	 Alert alert = new Alert(AlertType.CONFIRMATION);
               	 alert.setTitle("确认信息");
               	 alert.setHeaderText("请进行确认");
               	 alert.setContentText("确定延时该订单吗？");
               	 
               	 Optional<ButtonType> result = alert.showAndWait();
               	 if (result.get() == ButtonType.OK){
               	     // 
               	 } else {
               	     // 
               	 }
                }
            });
    		break;
    	case EDIT:
    		cellButton.setText("编辑");
    	default:
    		cellButton.setText("");
    		
    	}

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
	
}
