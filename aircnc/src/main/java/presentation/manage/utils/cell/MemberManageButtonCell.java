package presentation.manage.utils.cell;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import presentation.manage.model.MemberManageModel;

/**
 * FunctionButtons use a hbox(button) to show the operations.
 * 
 * @author paranoia
 *
 */
public class MemberManageButtonCell extends TableCell<MemberManageModel, Boolean>{
	Button[] cellButton;
	HBox buttons;
	UserType type;
    
    public MemberManageButtonCell(UserType type){
    	this.type = type;
    	createButtons();
    }
    
    public void createButtons(){
    	buttons = new HBox();
    	cellButton = new Button[]{ createButtons(ButtonType.CHECK), createButtons(ButtonType.MODIFY)};
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
    private Button createButtons(ButtonType type){
    	Button newButton = new Button();
    	switch(type){
    	case CHECK:
    		newButton.setText("查看");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    		break;
    	case MODIFY:
    		newButton.setText("查看");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    		break;
    	case DELETE:
    		newButton.setText("查看");
    		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
    		break;
    	default:
    			
    	}
    	
    	
    	 newButton.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent t) {
            	 
            	 
             }
         });
    	 
    	return newButton;
    }
    
    /**
     * Display button if the row is not empty
     * @param status, empty
     */
   /* @Override
    protected void updateItem(boolean b, boolean empty) {
        super.updateItem(b, empty);
        if(!empty){
        		createButtons();
        		setGraphic(buttons);
        }
    }*/
	
}
