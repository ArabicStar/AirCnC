package presentation.market.utils.cell;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import presentation.market.model.CheckOrderModel;

public class CheckButtonCell extends TableCell<CheckOrderModel, ButtonName>{
	final Button cellButton;
	final ButtonName name;
	
    
    public CheckButtonCell(ButtonName name){
    	cellButton = new Button();
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
    	case CHECK:
    		cellButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    		cellButton.setText("查看"); 
    		cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
               	 Alert alert = new Alert(AlertType.CONFIRMATION);
               	 alert.setTitle("订单详情");
               	 alert.setHeaderText("请进行确认");
               	 alert.setContentText("确定执行该订单吗？");
               	 
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
