package presentation.member.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import presentation.member.model.MyOrderModel;
import presentation.member.view.myorder.fxml.MemberOrderMainController;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

/**
 * FunctionButtons use a hbox(button) to show the operations.
 * 
 * @author paranoia
 *
 */
public class FunctionButtons extends TableCell<MyOrderModel, OrderVo>{
	
	private Button[] cellButton;
	private HBox buttons;
	private OrderStatus status;
	private OrderVo order;
	private MemberOrderMainController controller;
    
    public FunctionButtons(MemberOrderMainController controller){
    	status = OrderStatus.EXECUTED;
    	createFunctionButtons(status);
    	setController(controller);
    }
    
    public void setController(MemberOrderMainController controller){
    	this.controller = controller;
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
    				createButtons(FunctionButtonType.CHECK)
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
            		 //waiting for 订单详情
            		 break;
            	 case REVIEW: 
            		 if(order != null)
            			 controller.addCommentPane(order);
             		 break;
            	 case CANCEL: 
            		 if(order != null)
            			 controller.cancelOrder(order);
              		 break;
            	 case APPEAL: 
            		 if(order != null)
            			 controller.addCommentPane(order);
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
    protected void updateItem(OrderVo vo, boolean empty) {
        super.updateItem(vo, empty);
        if(!empty){
        		this.status = vo.getStatus();
        		this.order = vo;
        		createFunctionButtons(status);
        		setGraphic(buttons);
        }
    }
	
}
