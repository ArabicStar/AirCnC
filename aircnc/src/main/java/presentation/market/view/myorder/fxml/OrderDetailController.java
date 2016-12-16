package presentation.market.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class OrderDetailController implements Initializable{

	private MyOrderController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void close() {
		// TODO 下面remove方法没有写
		System.out.println("关闭");
		controller.removeOrderDetail();
	}

	public void setController(MyOrderController controller) {
		this.controller = controller;
	}
	
}
