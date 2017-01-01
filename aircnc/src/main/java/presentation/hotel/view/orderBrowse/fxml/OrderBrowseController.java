package presentation.hotel.view.orderBrowse.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import interactor.hotel.HotelOrderInteractor;
import interactor.impl.hotel.HotelOrderCourier;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.SearchOrderAccessor;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.HotelOrderManager;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import presentation.hotel.model.OrderModel;
import presentation.hotel.utils.cell.OrderButtonCell;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class OrderBrowseController implements Initializable{

	@SuppressWarnings("unused")
	private HotelCenterController controller;
	
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
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> userName;
	
	@FXML
    private TableColumn<OrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<OrderModel,String> status;
	
	@FXML
	private TableColumn<OrderModel,String> userId;
	
	@FXML
	private TableColumn<OrderModel,String> orderId;
	
	@FXML
	private TableColumn<OrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<OrderModel,OrderVo> operation;
	
	private ObservableList<OrderModel> models;
	
	private HotelOrderManager manager;
	
	private SearchOrderAccessor accessor;
	
	private HotelOrderInteractor interactor;
	
	private OrderBrowseController orderController = this;
	
	Set<OrderStatus> states;

	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		interactor = HotelOrderCourier.getInstance();
		interactor.getHotelAllOrders();

		manager = HotelOrderManagerImpl.getInstance();

		accessor = SearchOrderAccessorImpl.getInstance();
	}
	
    
    public void executeOrder(OrderVo vo){
    	accessor.setOrderVo(vo);
    	interactor.executeOrder();
    	refresh();
    }
    
    public void delayOrder(OrderVo vo){
    	accessor.setOrderVo(vo);
    	interactor.delayOrder();
    	refresh();
    }
    
    public void refresh() {
		accessor.setSearchTarget(states);
		interactor.getHotelOrdersByStatus();
		models = manager.getOrderList();
		orderTable.getItems().clear();
		orderTable.setItems(models);
	}
	
	@FXML
	public void handleQuery(){
		states = new HashSet<OrderStatus>();		
		if(finished.isSelected()){
			states.add(OrderStatus.EXECUTED);
			states.add(OrderStatus.REVIEWED);
		}
		
		if(unfinished.isSelected()){
			states.add(OrderStatus.UNEXECUTED);
		}
		
		if(exception.isSelected()){
			states.add(OrderStatus.ABNORMAL);
			states.add(OrderStatus.APPEALING);
		}
		
		if(cancelled.isSelected()){
			states.add(OrderStatus.REPEALED);
		}
		accessor.setSearchTarget(states);
		interactor.getHotelOrdersByStatus();
		
		models = manager.getOrderList();
		orderTable.getItems().clear();
		orderTable.setItems(models);
		userName.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
		userId.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		status.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<OrderModel, OrderVo>, 
                ObservableValue<OrderVo>>() {

            public ObservableValue<OrderVo> call(TableColumn.CellDataFeatures<OrderModel, OrderVo> p) {
            	return new SimpleObjectProperty<OrderVo>(p.getValue().getOperation());
            }
        });
		

		operation.setCellFactory(
                new Callback<TableColumn<OrderModel, OrderVo>, TableCell<OrderModel, OrderVo>>() {

            public TableCell<OrderModel, OrderVo> call(TableColumn<OrderModel, OrderVo> p) {
                return new OrderButtonCell(orderController);
            }    
        });
		
	}


}
