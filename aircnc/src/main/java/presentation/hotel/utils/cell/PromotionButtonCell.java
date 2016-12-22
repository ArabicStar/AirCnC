package presentation.hotel.utils.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import presentation.hotel.model.HotelPromotionModel;
import presentation.hotel.utils.dialog.PlainDialog;
import presentation.hotel.view.hotelPromotion.fxml.HotelPromotionMainController;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class PromotionButtonCell extends TableCell<HotelPromotionModel, PromotionVo>{

	private Button[] cellButton;
	private HBox buttons;
	private PromotionVo vo;
	private boolean practical = true;
	
	private HotelPromotionMainController controller;
	
	public PromotionButtonCell(HotelPromotionMainController controller){
    	createAllButtons();
    	setController(controller);
    }
    
    public void setController(HotelPromotionMainController controller){
    	this.controller = controller;
    }
    
    public void createAllButtons(){   	
    	buttons = new HBox();
    	buttons.setAlignment(Pos.CENTER_RIGHT);
    	
    	if(practical){
    		cellButton = new Button[]{ createButtons(ButtonName.RECALL) };  
    	}else{
    		cellButton = new Button[]{ 
        			createButtons(ButtonName.SEND) ,createButtons(ButtonName.UPDATE) ,
        			createButtons(ButtonName.DELETE)};  
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
     private Button createButtons(ButtonName type){
     	Button newButton = new Button();
     	if(type==ButtonName.UPDATE){
     		newButton.setText("修改");
     		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
     	}else if(type==ButtonName.SEND){
     		newButton.setText("发布");
     		newButton.setStyle("-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
     	}else if(type==ButtonName.RECALL){
     		newButton.setText("撤回");
     		newButton.setStyle("-fx-background-color: #F0787A;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
     	}else if(type==ButtonName.DELETE){
     		newButton.setText("删除");
     		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
     	}else{
     		newButton.setText("");
     		newButton.setStyle("-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20;");
     	}
     	
     	
     	 newButton.setOnAction(new EventHandler<ActionEvent>(){

              @Override
              public void handle(ActionEvent t) {
             	 switch(type){
             	 case UPDATE: 
             		 if(vo != null&&!vo.getPractical()){
             			controller.addDetailPane(vo); 
             		 }
             		 break;
             	 case DELETE: 
             		 if(vo != null&&!vo.getPractical()){
             			 controller.deletePromotion((HotelPromotionVo) vo);
             			 PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
     					"删除成功","已删除促销策略");
             		 	alert.showDialog();
             		 }
              		 break;
             	 case RECALL: 
             		 if(vo != null&&vo.getPractical()){
             			controller.addAndUpdate((HotelPromotionVo) new PromotionVoBuilder(vo).setPractical(false).getPromotionInfo());
             			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
            					"撤回成功","已撤回促销策略");
            			alert.showDialog();
             		 }
             		 
               		 break;
             	 case SEND: 
             		 if(vo != null&&!vo.getPractical()){
             			controller.addAndUpdate((HotelPromotionVo) new PromotionVoBuilder(vo).setPractical(true).getPromotionInfo());
             			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,
            					"发布成功","已发布促销策略");
            			alert.showDialog();
             		 }
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
     protected void updateItem(PromotionVo vo, boolean empty) {
         super.updateItem(vo, empty);
         if(!empty){
         		this.vo = vo;
         		this.practical = vo.getPractical();
         		createAllButtons();
         		setGraphic(buttons);
         }
     }
}
