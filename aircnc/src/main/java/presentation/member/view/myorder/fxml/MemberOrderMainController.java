package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.manager.MyOrderManager;
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
	
	/**
	 * set the manager
	 * @param manager
	 */
	public void setManager(MyOrderManager manager){
		this.manager=manager;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
	}

	/**
	 * add the content to the tableview
	 */
	@FXML
	public void handleQuery(){
		orderTable.setItems(manager.getOrderList());
		
		
		System.out.println(manager.getOrderList().size());
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
	
	
	
}
