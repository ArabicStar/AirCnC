package presentation.member.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import presentation.member.view.myorder.fxml.MemberAppealController;
import vo.order.OrderVo;

public class MemberAppealPane {
	
	private Pane orderLayout;
	private MemberAppealController controller;
	private OrderVo vo;


	public MemberAppealPane(OrderVo order){
		init();
		this.vo = order;
	}

	public void init() {
        try {
            // Load Member Appeal overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberAppeal.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.load();
            controller = (MemberAppealController)loader.getController();
            orderLayout = loader.getRoot();
            controller.setOrderVo(vo);
            
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
