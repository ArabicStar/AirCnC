package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.model.MyorderModel;
import presentation.member.utils.FunctionButtons;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class MemberOrderMainController implements Initializable{
	
	private ClientCenterController controller;
	
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
	private TableColumn<MyorderModel,OrderStatus> operation;
	
	private MyOrderManager manager;
	
	/**
	 * set the controller
	 * @param controller
	 */
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
		
		manager = MyOrderManagerImpl.getInstance();
	}

	/**
	 * add the content to the tableview
	 */
	@FXML
	public void handleQuery(){
		orderTable.setItems(manager.getOrderList());
		
		
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<MyorderModel, OrderStatus>, 
                ObservableValue<OrderStatus>>() {

            public ObservableValue<OrderStatus> call(TableColumn.CellDataFeatures<MyorderModel, OrderStatus> p) {
            	return new SimpleObjectProperty<OrderStatus>(p.getValue().getOperation());
            }
        });
	

		operation.setCellFactory(
                new Callback<TableColumn<MyorderModel,OrderStatus>, TableCell<MyorderModel, OrderStatus>>() {
            public TableCell<MyorderModel,OrderStatus> call(TableColumn<MyorderModel, OrderStatus> p) {
                return new FunctionButtons();
            }       
        });
	}
	
	/**
	 * when user deliver a comment or an appeal,
	 * update the ObservableList
	 * @param isSelectCol
	 */
//	private void updateObservableListProperties(TableColumn<MyorderModel,OrderStatus> operation, TableColumn<MyorderModel,String> state){
//
//		operation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyorderModel,OrderSatatus>>() {
//
//			@Override
//			public void handle(CellEditEvent<MyorderModel,OrderSatatus> event) {
//				String newState;
//				switch(event.getNewValue()){
//		        case ABNORMAL: 
//		        	newState = "异常";  break;
//		        case EXECUTED: 
//		        	newState = "已执行";  break;
//		        case UNEXECUTED: 
//		        	newState = "未执行";  break;
//		        case REPEALED: 
//		        	newState = "撤销";  break;
//		        case REVIEWED: 
//		        	newState = "已评价";  break;
//		        case APPEALING: 
//		        	newState = "申诉中";  break;
//		        default: 
//		        	newState = "";  break;
//		        }
//				event.getTableView().getItems().get(event.getTablePosition().getRow()).setAmount(event.getNewValue());
//			}
//		});
//	}   
	
	
	
}
