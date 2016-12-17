package presentation.member.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.view.myorder.fxml.MemberCommentController;
import vo.order.OrderVo;

public class MemberCommentPane {
	
	private Pane orderLayout;
	private MemberCommentController controller;
	private OrderVo vo;


	public MemberCommentPane(OrderVo order){
		init();
		this.vo = order;
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
