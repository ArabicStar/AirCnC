package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import presentation.member.ClientCenterController;
import presentation.member.view.myorder.MyorderModel;
import vo.order.OrderVo;

public class MemberOrderMainController implements Initializable{
	
	private ClientCenterController controller;
	
	private List<OrderVo> orders;
	
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
	private TableView<MyorderModel> orderTable;
	
	@FXML
	private TableColumn<MyorderModel, String> hotelName;
	
	@FXML
    private TableColumn<MyorderModel, String> checkInTime;
	
	@FXML
	private TableColumn<MyorderModel,String> state;
	
	@FXML
	private TableColumn<MyorderModel,String> timeAndSum;
	
	@FXML
	private TableColumn<MyorderModel,String> totalPrice;
	
	@FXML
	private TableColumn<MyorderModel,String> operation;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void handleQuery(){
		orderTable.setItems(controller.getData());
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
	}
	
	public void setContent(ObservableList<MyorderModel> orderdata){
		
	}
	
	public void setOrders(List<OrderVo> orders){
		this.orders = orders;
		initOrderList();
	}
	
	private void initOrderList(){
		Iterator<OrderVo> it = orders.iterator();
		while(it.hasNext()){
			addOrderRecord(it.next());
		}
	}
	
	private void addOrderRecord(OrderVo order){
		
	}
	
}
