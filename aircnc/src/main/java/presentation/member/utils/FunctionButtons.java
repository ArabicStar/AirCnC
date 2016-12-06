package presentation.member.utils;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import presentation.member.model.MyorderModel;
import utils.info.order.OrderStatus;

/**
 * FunctionButtons use a hbox(button) to show the operations.
 * 
 * @author paranoia
 *
 */
public class FunctionButtons extends TableCell<MyorderModel, OrderStatus>{
	
	Button[] cellButton;
	HBox buttons;
	OrderStatus status;
    
    public FunctionButtons(){
    	status = OrderStatus.EXECUTED;
    	createFunctionButtons(status);
    }
    
    public void createFunctionButtons(OrderStatus status){
    	this.status = status;
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
    	case REPEALED:  case APPEALING:   case REVIEWED:
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
            	 switch(type){
            	 case CHECK: 
            		PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
         			"查看订单","让我等会写一下这个东西，不对，让万总写一下");
         			alert.showDialog();
            		 break;
            	 case REVIEW: 
             		PlainDialog alert2 = new PlainDialog(AlertType.INFORMATION,
          			"订单评价","等我写出来。。");
          			alert2.showDialog();
             		 break;
            	 case CANCEL: 
              		PlainDialog alert3 = new PlainDialog(AlertType.INFORMATION,
           			"取消订单","你确定取消该订单吗？");
           			alert3.showDialog();
              		 break;
            	 case APPEAL: 
              		PlainDialog alert4 = new PlainDialog(AlertType.INFORMATION,
           			"订单申诉","等我写出来。。");
           			alert4.showDialog();
              		 break;
            	 }
             }
         });
    	 
    	return newButton;
    }
    
    /**
     * Display button if the row is not empty
     * @param status, empty
     */
    @Override
    protected void updateItem(OrderStatus status, boolean empty) {
        super.updateItem(status, empty);
        if(!empty){
        		this.status = status;
        		createFunctionButtons(status);
        		setGraphic(buttons);
        }
    }
	
}
