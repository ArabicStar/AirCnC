package presentation.member.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import presentation.member.view.myorder.fxml.MemberOrderMainController;
import vo.order.OrderVo;

public class MemberOrderMainPane {
	
	private Pane orderLayout;
	private MemberOrderMainController controller;

	public MemberOrderMainPane(){
		init();
	}
	
	public void init() {
        try {
            // Load sign in overview.
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource("fxml/MemberOrderMain.fxml");
            loader.setLocation(location);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            //javafx.scene.Parent root = (javafx.scene.Parent) loader.load(location.openStream());
            loader.load();
            controller = (MemberOrderMainController)loader.getController();
            orderLayout = loader.getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberOrderMainController getController(){
    	return this.controller;
    }
    
    public Node getPane(){
    	return orderLayout;
    }
}
