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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import presentation.market.MarketCenterController;
import presentation.market.model.MyOrderModel;
import presentation.market.utils.FunctionButtons;
import presentation.market.view.myorder.OrderDetailPane;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class MyOrderController implements Initializable {

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
	private TableColumn<MyOrderModel, String> state;

	@FXML
	private TableColumn<MyOrderModel, String> timeAndSum;

	@FXML
	private TableColumn<MyOrderModel, String> totalPrice;

	@FXML
	private TableColumn<MyOrderModel, OrderStatus> operation;
	
	private AnchorPane rootLayout;
	
	private OrderDetailPane orderDetailPane;
	
	@FXML
	private void handleQuery() {
		LocalDateTime entryTime = LocalDateTime.now();

		OrderVo order = new OrderVoBuilder().setEntryTime(entryTime).setHasChildren(false)
				.setLastTime(entryTime).setOrderId("2016121010001234").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2)
				.setStatus(OrderStatus.EXECUTED).getOrderInfo();
		MyOrderModel orderModel = new MyOrderModel(order);

		OrderVo order1 = new OrderVoBuilder().setEntryTime(entryTime).setHasChildren(true)
				.setLastTime(entryTime).setOrderId("2016122501011234").setPeopleNumber(3)
				.setOriginalPrice(1400).setRoomNumber(1).setRoomType("三人间").setStayDays(7)
				.setStatus(OrderStatus.UNEXECUTED).getOrderInfo();
		MyOrderModel orderModel1 = new MyOrderModel(order1);

		ObservableList<MyOrderModel> orderData = FXCollections.observableArrayList();
		orderData.add(orderModel);
		orderData.add(orderModel1);

		orderTable.setItems(orderData);
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());

		operation.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<MyOrderModel, OrderStatus>, ObservableValue<OrderStatus>>() {

					public ObservableValue<OrderStatus> call(
							TableColumn.CellDataFeatures<MyOrderModel, OrderStatus> p) {
						return new SimpleObjectProperty<OrderStatus>(p.getValue().getOperation());
					}
				});
		
		operation.setCellFactory(

				new Callback<TableColumn<MyOrderModel, OrderStatus>, TableCell<MyOrderModel, OrderStatus>>() {
					public TableCell<MyOrderModel, OrderStatus> call(TableColumn<MyOrderModel, OrderStatus> p) {
						return new FunctionButtons();
					}
				});
//		functionButtons.setController(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}
	
	public void addOrderDetail(MyOrderModel model) {
		OrderDetailPane pane = new OrderDetailPane(model);
		
		if(rootLayout == null) {
			System.out.println("空");
		}
		// TODO
		rootLayout.getChildren().add(pane.getPane());
		AnchorPane.setTopAnchor(pane.getPane(), 100.0);
		pane.getController().setController(this);
		
	}

	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	public void removeOrderDetail() {
		// TODO:增加该方法
	}
}
