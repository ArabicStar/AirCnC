package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.model.MyorderModel;
import presentation.member.utils.FunctionButtonType;
import presentation.member.utils.FunctionButtons;
import utils.info.order.OrderStatus;
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
	private TableColumn<MyorderModel,OrderStatus> operation;
	
	private OrderStatus status;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
	}

	
	@FXML
	public void handleQuery(){
		orderTable.setItems(controller.getMyOrderData());
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
                status=p.getValue().getOperation();
            	return new SimpleObjectProperty<OrderStatus>(p.getValue().getOperation());
            }
        });
		

		operation.setCellFactory(
                new Callback<TableColumn<MyorderModel,OrderStatus>, TableCell<MyorderModel, OrderStatus>>() {

            public TableCell<MyorderModel,OrderStatus> call(TableColumn<MyorderModel, OrderStatus> p) {
                if(status!=null) return new FunctionButtons(status);
                return new FunctionButtons(OrderStatus.EXECUTED);
            }       
        });
		//operation.setCellValueFactory(cellData -> cellData.getValue().operationProperty());
	}
	
	public void setContent(ObservableList<MyorderModel> orderdata){
		
	}
	
}
