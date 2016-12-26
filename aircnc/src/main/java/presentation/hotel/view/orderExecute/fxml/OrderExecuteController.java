package presentation.hotel.view.orderExecute.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import interactor.hotel.HotelOrderInteractor;
import interactor.impl.hotel.HotelOrderCourier;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import presentation.hotel.utils.cell.ExecuteCell;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class OrderExecuteController implements Initializable {

	private HotelCenterController controller;

	@FXML
	private TableView<OrderModel> orderTable;

	@FXML
	private TableColumn<OrderModel, String> userName;

	@FXML
	private TableColumn<OrderModel, String> userId;

	@FXML
	private TableColumn<OrderModel, String> orderId;

	@FXML
	private TableColumn<OrderModel, String> checkInTime;

	@FXML
	private TableColumn<OrderModel, String> timeAndSum;

	@FXML
	private TableColumn<OrderModel, String> totalPrice;

	@FXML
	private TableColumn<OrderModel, OrderVo> operation;

	private OrderExecuteController orderController = this;

	private ObservableList<OrderModel> models;

	private HotelOrderManager manager;

	private SearchOrderAccessor accessor;

	private HotelOrderInteractor interactor;

	Set<OrderStatus> states = new HashSet<OrderStatus>();

	public void setCenterController(HotelCenterController controller) {
		this.controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		states.add(OrderStatus.UNEXECUTED);

		manager = HotelOrderManagerImpl.getInstance();

		accessor = SearchOrderAccessorImpl.getInstance();
		accessor.setSearchTarget(states);
		interactor = HotelOrderCourier.getInstance();
		interactor.getHotelOrdersByStatus();

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initOrder();
			}
		});

	}

	public void executeOrder(OrderVo vo) {
		accessor.setOrderVo(vo);
		interactor.executeOrder();
		refresh();
	}

	public void refresh() {
		interactor.getHotelOrdersByStatus();
		models = manager.getOrderList();
		orderTable.setItems(models);
	}

	public void initOrder() {
		accessor.setSearchTarget(states);
		models = manager.getOrderList();
		orderTable.setItems(models);

		userName.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
		userId.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		operation.setSortable(false);

		operation.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<OrderModel, OrderVo>, ObservableValue<OrderVo>>() {

					public ObservableValue<OrderVo> call(TableColumn.CellDataFeatures<OrderModel, OrderVo> p) {
						return new SimpleObjectProperty<OrderVo>(p.getValue().getOperation());
					}
				});

		operation.setCellFactory(new Callback<TableColumn<OrderModel, OrderVo>, TableCell<OrderModel, OrderVo>>() {

			public TableCell<OrderModel, OrderVo> call(TableColumn<OrderModel, OrderVo> p) {
				return new ExecuteCell(orderController);
			}
		});

	}

}
