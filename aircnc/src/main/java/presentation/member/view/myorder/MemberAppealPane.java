package presentation.member.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import presentation.member.view.myorder.fxml.MemberAppealController;
import vo.order.OrderVo;

public class MemberAppealPane {
	
	private Pane orderLayout;
	private MemberAppealController controller;
	private OrderVo vo;


	public MemberAppealPane(OrderVo order){
		this.vo = order;
		init();
	}

	public void init() {
        try {
            // Load Member Appeal overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberAppeal.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = loader.getController();
            orderLayout = loader.getRoot();
            controller.setOrderVo(vo);
            
            DropShadow ds = new DropShadow();
            
            ds.setOffsetY(5.0);
            ds.setOffsetX(5.0);
            ds.setColor(Color.GRAY);
            
            orderLayout.setEffect(ds);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberAppealController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
