package presentation.market.view.myorder.fxml;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.market.MarketCenterController;
import presentation.market.model.MyOrderModel;
import presentation.market.utils.FunctionButtons;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

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
	private TableView<MyOrderModel> orderTable;
	
	@FXML
	private TableColumn<MyOrderModel, String> hotelName;
	
	@FXML
    private TableColumn<MyOrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<MyOrderModel,String> state;
	
	@FXML
	private TableColumn<MyOrderModel,String> timeAndSum;
	
	@FXML
	private TableColumn<MyOrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<MyOrderModel,OrderStatus> operation;
	
	@FXML
	private void handleQuery() {
		LocalDateTime entryTime = LocalDateTime.now();
		
		OrderVo order = new OrderVo();
		order.setEntryTime(entryTime).setHasChildren(false).setHotelId(1000)
		.setHotelName("乐天玛特").setLastTime(entryTime).setOrderId("2016121010001234").setPeopleNumber(3).setPrice(200)
		.setIsReviewed(false).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
		.setStatus(OrderStatus.EXECUTED).setUserName("南京大学渣");
		MyOrderModel orderModel = new MyOrderModel(order);

		
		OrderVo order1 = new OrderVo();
		order1.setEntryTime(entryTime).setHasChildren(true).setHotelId(2412)
		.setHotelName("百祥速8").setLastTime(entryTime).setOrderId("2016122501011234").setPeopleNumber(3).setPrice(1400)
		.setIsReviewed(false).setRoomNumber(1).setRoomType("三人间").setStayDays(7).setUserId(208032121)
		.setStatus(OrderStatus.UNEXECUTED).setUserName("南京大学渣");
		MyOrderModel orderModel1 = new MyOrderModel(order1);
		
		ObservableList<MyOrderModel> orderData = FXCollections.observableArrayList();
		orderData.add(orderModel);
		orderData.add(orderModel1);
		
		
		
		orderTable.setItems(orderData);
		System.out.println("你看我应该写的没有问题" + orderTable.hashCode());
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<MyOrderModel, OrderStatus>, 
                ObservableValue<OrderStatus>>() {

            public ObservableValue<OrderStatus> call(TableColumn.CellDataFeatures<MyOrderModel, OrderStatus> p) {
            	return new SimpleObjectProperty<OrderStatus>(p.getValue().getOperation());
            }
        });
		
		operation.setCellFactory(
			
				new Callback<TableColumn<MyOrderModel, OrderStatus>, TableCell<MyOrderModel, OrderStatus>>() {
					public TableCell<MyOrderModel, OrderStatus> call(TableColumn<MyOrderModel, OrderStatus> p) {
						System.out.println("猜一猜谁先动手");
						return new FunctionButtons();
					}
				});
		
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}

}
