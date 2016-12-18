package presentation.member.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import presentation.member.view.myorder.fxml.MemberCommentController;
import vo.order.OrderVo;

public class MemberCommentPane {
	
	private Pane orderLayout;
	private MemberCommentController controller;
	private OrderVo vo;


	public MemberCommentPane(OrderVo order){
		this.vo = order;
		init();
	}

	public void init() {
        try {
            // Load Member Comment overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberComment.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (MemberCommentController)loader.getController();
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

    public MemberCommentController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
    
}
