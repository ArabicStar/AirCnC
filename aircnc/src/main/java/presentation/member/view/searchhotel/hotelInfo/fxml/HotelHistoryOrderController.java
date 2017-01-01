package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private ObservableList<MyOrderModel> models;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->initOrders());
	}
	
	
	public void initOrders(){
		Iterator<MyOrderModel> it = model.getHistoryOrder().iterator();
		models = FXCollections.observableArrayList();
		while(it.hasNext() && model.getHistoryOrder().size()>0){
			models.add(it.next());
		}
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
	
	@FXML
	public void handleReverse(){
		controller.handleReverse();
	}
}
