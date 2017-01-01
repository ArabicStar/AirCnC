package presentation.market.view.unexecuteOrder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.market.MarketServiceCourier;
import interactor.market.MarketServiceInteractor;
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
import presentation.market.MarketCenterController;
import presentation.market.manager.UnexecutedOrderManager;
import presentation.market.manager.impl.UnexecutedOrderManagerImpl;
import presentation.market.model.OrderModel;
import presentation.market.utils.cell.UnexecuteCell;
import vo.order.OrderVo;

public class UnexecuteOrderController implements Initializable{
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
	private TableColumn<OrderModel, String> timeAndSum;

	@FXML
	private TableColumn<OrderModel, String> price;

	@FXML
	private TableColumn<OrderModel, OrderVo> operation;
	
	private MarketServiceInteractor interactor;
	
	private UnexecutedOrderManager manager;
	
	private ObservableList<OrderModel> models;
	
//	private UnexecuteOrderController orderController = this;

	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		interactor = MarketServiceCourier.getInstance();
		interactor.getUnexecutedOrder();

		manager = UnexecutedOrderManagerImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initOrder();
			  }
		});
	}
	
	
	public void initOrder(){	
		
		models = manager.getOrderList();
		orderTable.setItems(models);
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		userId.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
		timeAndSum.setCellValueFactory(cellData->cellData.getValue().timeAndSumProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
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
                return new UnexecuteCell();
            }    
        });
		
	}
}
