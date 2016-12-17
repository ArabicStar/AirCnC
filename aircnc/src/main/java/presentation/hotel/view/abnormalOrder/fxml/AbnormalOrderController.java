package presentation.hotel.view.abnormalOrder.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
import presentation.hotel.HotelCenterController;
import presentation.hotel.accessor.SearchOrderAccessor;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.HotelOrderManager;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import presentation.hotel.model.OrderModel;
import presentation.hotel.utils.cell.ButtonCell;
import presentation.hotel.utils.cell.ButtonName;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class AbnormalOrderController implements Initializable{
private HotelCenterController controller;
	
	private List<OrderVo> orders;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> userName;
	
	@FXML
	private TableColumn<OrderModel,String> userId;	
	
	@FXML
	private TableColumn<OrderModel,String> orderId;
	
	@FXML
    private TableColumn<OrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<OrderModel,String> timeAndSum;
	
	@FXML
	private TableColumn<OrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<OrderModel,ButtonName> operation;
	
	private HotelOrderManager manager;
	
	private SearchOrderAccessor accessor;
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		if(!HotelOrderManagerImpl.isLaunched()){
			HotelOrderManagerImpl.launch();
		}
		manager = HotelOrderManagerImpl.getInstance();
		
		if(!SearchOrderAccessorImpl.isLaunched()){
			SearchOrderAccessorImpl.launch();
		}
		accessor = SearchOrderAccessorImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initExecuteOrder();
			  }
		});

	}
	
	public void initExecuteOrder(){
		Set<OrderStatus> states = new HashSet<OrderStatus>();
		states.add(OrderStatus.ABNORMAL);
		
		accessor.setSearchTarget(states);
		orderTable.setItems(manager.getOrderList());
		
		userName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
		userId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<OrderModel, ButtonName>, 
                ObservableValue<ButtonName>>() {

            public ObservableValue<ButtonName> call(TableColumn.CellDataFeatures<OrderModel, ButtonName> p) {
            	return new SimpleObjectProperty<ButtonName>(p.getValue().getOperation());
            }
        });
		

		operation.setCellFactory(
                new Callback<TableColumn<OrderModel, ButtonName>, TableCell<OrderModel, ButtonName>>() {

            public TableCell<OrderModel, ButtonName> call(TableColumn<OrderModel, ButtonName> p) {
                return new ButtonCell(ButtonName.EXECUTE);
            }    
        });
		
	}
	
//	public void test(){
//		ObservableList<OrderModel> orderData = FXCollections.observableArrayList();
//    	orderData.add(new OrderModel("小手表","233","101","2016-10-09","5晚/1间","290元"));
//		orderData.add(new OrderModel("小手表","233","102","2016-10-12","2晚/1间","1000元"));
//		orderData.add(new OrderModel("小手表","233","103","2016-10-15","10晚/1间","400元"));
//		orderData.add(new OrderModel("小手表","233","104","2016-10-30","1晚/10间","2950元"));
//		orderTable.setItems(orderData);
//		userName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
//		
//		userId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
//		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
//		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
//		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
//		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
//		operation.setSortable(false);
//		
//		operation.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<OrderModel, ButtonName>, 
//                ObservableValue<ButtonName>>() {
//
//            public ObservableValue<ButtonName> call(TableColumn.CellDataFeatures<OrderModel, ButtonName> p) {
//            	return new SimpleObjectProperty<ButtonName>(p.getValue().getOperation());
//            }
//        });
//		
//
//		operation.setCellFactory(
//                new Callback<TableColumn<OrderModel, ButtonName>, TableCell<OrderModel, ButtonName>>() {
//
//            public TableCell<OrderModel, ButtonName> call(TableColumn<OrderModel, ButtonName> p) {
//                return new ButtonCell(ButtonName.DELAY);
//            }    
//        });
//	}
}
