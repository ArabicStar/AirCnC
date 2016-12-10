package presentation.market.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.hotel.model.CheckOrderModel;
import presentation.hotel.utils.ButtonName;
import presentation.market.MarketCenterController;

public class MyOrderController implements Initializable{
	
	@SuppressWarnings("unused")
	private MarketCenterController controller;
	
	@FXML
	private CheckBox unfinished;
	
	@FXML
	private CheckBox finished;
	
	@FXML
	private CheckBox exception;
	
	@FXML
	private CheckBox cancelled;
	
	@FXML
	private Button query;
	
	@FXML
	private TableView<CheckOrderModel> orderTable;
	
	@FXML
	private TableColumn<CheckOrderModel, String> userName;
	
	@FXML
    private TableColumn<CheckOrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<CheckOrderModel,String> status;
	
	@FXML
	private TableColumn<CheckOrderModel,String> userId;
	
	@FXML
	private TableColumn<CheckOrderModel,String> orderId;
	
	@FXML
	private TableColumn<CheckOrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<CheckOrderModel,ButtonName> operation;
	
	@FXML
	private void handleQuery() {
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}

}
