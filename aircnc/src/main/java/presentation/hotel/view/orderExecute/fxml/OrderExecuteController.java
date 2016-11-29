package presentation.hotel.view.orderExecute.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.hotel.HotelCenterController;
import presentation.hotel.view.orderExecute.UnexecutedOrderModel;
import vo.order.OrderVo;

public class OrderExecuteController implements Initializable{
	
	private HotelCenterController controller;
	
	private List<OrderVo> orders;
	
	@FXML
	private TableView<UnexecutedOrderModel> orderTable;
	
	@FXML
	private TableColumn<UnexecutedOrderModel, String> userName;
	
	@FXML
	private TableColumn<UnexecutedOrderModel,String> userId;	
	
	@FXML
	private TableColumn<UnexecutedOrderModel,String> orderId;
	
	@FXML
    private TableColumn<UnexecutedOrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<UnexecutedOrderModel,String> timeAndSum;
	
	@FXML
	private TableColumn<UnexecutedOrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<UnexecutedOrderModel,Button> operation;
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		orderTable.setEditable(false);

	}
	
	public void test(){
		orderTable.setItems(controller.getData());
		userName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
		
		userId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		operation.setCellValueFactory(cellData -> cellData.getValue().operationProperty());
	}
	
	
}
