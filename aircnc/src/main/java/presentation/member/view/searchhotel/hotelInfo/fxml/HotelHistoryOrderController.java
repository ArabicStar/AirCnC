package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.impl.order.OrderInfoCourier;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.member.accessor.impl.HotelNameAccessorImpl;
import presentation.member.manager.HistoryOrderManager;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.impl.HistoryOrderManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.model.MyOrderModel;
import presentation.member.model.SearchHotelsModel;

public class HotelHistoryOrderController implements Initializable{
	
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

	private HotelInfoMainController controller;
	private SearchHotelsModel model;
	private HistoryOrderManager manager;
	private ObservableList<MyOrderModel> models;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = HistoryOrderManagerImpl.getInstance();
		Platform.runLater(()->initOrders());
	}
	
	
	public void initOrders(){
		OrderInfoCourier.getInstance().getOrderInfoByHotel();
		models = manager.getOrderList();
		orderTable.setItems(models);		
		
		hotelName.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
	}
	
	@FXML
	public void handlePage1(){
		controller.addHotelInfoOnePane();
	}
	
	@FXML
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	@FXML
	public void handlePage4(){
		controller.addHotelInfoFourPane();
	}
	
	public void setInfoMainController(HotelInfoMainController controller){
		this.controller=controller;
	}
	
	public void setModel(SearchHotelsModel model){
		this.model = model;
	}
}
