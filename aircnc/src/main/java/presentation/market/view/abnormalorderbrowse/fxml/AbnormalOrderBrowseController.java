package presentation.market.view.abnormalorderbrowse.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import interactor.impl.hotel.HotelOrderCourier;
import interactor.impl.market.MarketServiceCourier;
import interactor.market.MarketServiceInteractor;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import presentation.hotel.utils.cell.OrderButtonCell;
import presentation.market.MarketCenterController;
import presentation.market.accessor.AbnormalOrderAccessor;
import presentation.market.accessor.impl.AbnormalOrderAccessorImpl;
import presentation.market.manager.AbnormalOrderManager;
import presentation.market.manager.impl.AbnormalOrderManagerImpl;
import presentation.market.model.OrderModel;
import presentation.market.utils.cell.OrderCell;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;
import presentation.market.utils.cell.ButtonName;

public class AbnormalOrderBrowseController implements Initializable {
	@SuppressWarnings("unused")
	private MarketCenterController controller;

	@FXML
	private TableView<OrderModel> orderTable;

	@FXML
	private TableColumn<OrderModel, String> hotelName;

	@FXML
	private TableColumn<OrderModel, String> userId;

	@FXML
	private TableColumn<OrderModel, String> orderId;

	@FXML
	private TableColumn<OrderModel, String> checkInTime;

	@FXML
	private TableColumn<OrderModel, String> timeAndSum;

	@FXML
	private TableColumn<OrderModel, String> price;

	@FXML
	private TableColumn<OrderModel, ButtonName> operation;
	
	private MarketServiceInteractor interactor;
	
	private AbnormalOrderManager manager;
	
	private AbnormalOrderAccessor accessor;
	
	private OrderModel models;

	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		interactor = MarketServiceCourier.getInstance();
		interactor.getAbnormalOrder();

		manager = AbnormalOrderManagerImpl.getInstance();

		accessor = AbnormalOrderAccessorImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
//				  initOrder();
			  }
		});
	}
//	
//    
//    public void approveOrder(OrderVo vo){
//    	accessor.setOrderVo(vo);
//    	interactor.executeOrder();
//    	refresh();
//    }
//    
//    public void refresh() {
//		accessor.setSearchTarget(states);
//		interactor.getHotelOrdersByStatus();
//		models = manager.getOrderList();
//		orderTable.setItems(models);
//	}
//	
//	
//	public void initOrder(){	
//		
//		models = manager.getOrderList();
//		orderTable.setItems(models);
//		userName.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
//		userId.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
//		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
//		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
//		status.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
//		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
//		operation.setSortable(false);
//		
//		operation.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<OrderModel, OrderVo>, 
//                ObservableValue<OrderVo>>() {
//
//            public ObservableValue<OrderVo> call(TableColumn.CellDataFeatures<OrderModel, OrderVo> p) {
//            	return new SimpleObjectProperty<OrderVo>(p.getValue().getOperation());
//            }
//        });
//		
//
//		operation.setCellFactory(
//                new Callback<TableColumn<OrderModel, OrderVo>, TableCell<OrderModel, OrderVo>>() {
//
//            public TableCell<OrderModel, OrderVo> call(TableColumn<OrderModel, OrderVo> p) {
//                return new OrderButtonCell(orderController);
//            }    
//        });
//		
//	}

}
