package presentation.hotel.view.hotelPromotion;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import presentation.hotel.view.hotelPromotion.fxml.PromotionDetailController;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public class PromotionDetailPane {
	private Pane layout;
	private PromotionDetailController controller;
	private PromotionVo vo;


	public PromotionDetailPane(PromotionVo promotion){
		this.vo = promotion;
		init();
	}

	public void init() {
        try {
            // Load Member Comment overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/PromotionDetail.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (PromotionDetailController)loader.getController();
            layout = loader.getRoot();
            controller.setPromotionVo(vo);
            
            DropShadow ds = new DropShadow();
            
            ds.setOffsetY(5.0);
            ds.setOffsetX(5.0);
            ds.setColor(Color.GRAY);
            
            layout.setEffect(ds);
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PromotionDetailController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return layout;
    }
}
