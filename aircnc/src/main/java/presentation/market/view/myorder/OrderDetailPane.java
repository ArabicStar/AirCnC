package presentation.market.view.myorder;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import presentation.market.model.MyOrderModel;
import presentation.market.view.myorder.fxml.OrderDetailController;

public class OrderDetailPane {
	private AnchorPane orderPane;
	private OrderDetailController controller;
	private MyOrderModel model;
	public OrderDetailPane(MyOrderModel model) {
		init();
		this.setModel(model);
	}
	public void init() {
		 try {
	            // Load sign in overview.
	            FXMLLoader loader = new FXMLLoader();
	            URL location = getClass().getResource("fxml/OrderDetail.fxml");
	            loader.setLocation(location);
	            loader.setBuilderFactory(new JavaFXBuilderFactory());
	            loader.load();
	            controller = (OrderDetailController)loader.getController();
	            orderPane = loader.getRoot();
	            
	            DropShadow ds = new DropShadow();
	            
	            ds.setOffsetY(5.0);
	            ds.setOffsetX(5.0);
	            ds.setColor(Color.GRAY);
	            
	            orderPane.setEffect(ds);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public Node getPane() {
		return orderPane;
	}
	
	public OrderDetailController getController() {
		return controller;
	}
	public MyOrderModel getModel() {
		return model;
	}
	public void setModel(MyOrderModel model) {
		this.model = model;
	}
}
