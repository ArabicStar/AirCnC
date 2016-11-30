package presentation.member.view.myorder.utils;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.member.view.myorder.MyorderModel;
import utils.info.order.OrderStatus;

public class FunctionButtons extends TableCell<MyorderModel, OrderStatus>{
	
	final Button[] cellButton;
	final HBox buttons;
    
    public FunctionButtons(OrderStatus status){
    	buttons = new HBox();
    	switch(status){
    	case UNEXECUTED:
    		cellButton = new Button[]{ 
    				createButtons(FunctionButtonType.CHECK) , createButtons(FunctionButtonType.CANCEL) 
    				};    		
    		break;
    	case EXECUTED:
    		cellButton = new Button[]{ 
    				createButtons(FunctionButtonType.CHECK) , createButtons(FunctionButtonType.REVIEW) 
    				}; 
    		break;
    	case ABNORMAL:
    		cellButton = new Button[]{ 
    				createButtons(FunctionButtonType.CHECK) , createButtons(FunctionButtonType.APPEAL) 
    				}; 
    		break;
    	case REPEALED:
    		cellButton = new Button[]{ 
    				createButtons(FunctionButtonType.CHECK) 
    				}; 
    		break;
    	default:
    		cellButton = new Button[]{ 
    				createButtons(null)
    				}; 
    		
    	}
    	
    	for(Button b:cellButton){
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
    private Button createButtons(FunctionButtonType type){
    	Button newButton = new Button();
    	if(type==FunctionButtonType.CHECK){
    		newButton.setText("查看");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	}else if(type==FunctionButtonType.APPEAL){
    		newButton.setText("申诉");
    		newButton.setStyle("-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	}else if(type==FunctionButtonType.CANCEL){
    		newButton.setText("撤销");
    		newButton.setStyle("-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	}else if(type==FunctionButtonType.REVIEW){
    		newButton.setText("评论");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	}else{
    		newButton.setText("");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    	}
    	
    	
    	 newButton.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent t) {
            	 Alert alert = new Alert(AlertType.CONFIRMATION);
            	 alert.setTitle("确认信息");
            	 alert.setHeaderText("请进行确认");
            	 alert.setContentText("你确定吗？");
            	 
            	 Optional<ButtonType> result = alert.showAndWait();
            	 if (result.get() == ButtonType.OK){
            	     // 
            	 } else {
            	     // 
            	 }
             }
         });
    	 
    	return newButton;
    }
    //Display button if the row is not empty
    @Override
    protected void updateItem(OrderStatus status, boolean empty) {
        super.updateItem(status, empty);
        if(!empty){
        		setGraphic(buttons);
        }
    }
	
}
