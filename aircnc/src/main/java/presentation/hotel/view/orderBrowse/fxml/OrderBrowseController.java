package presentation.hotel.view.orderBrowse.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.hotel.HotelCenterController;
import presentation.hotel.model.CheckOrderModel;
import presentation.hotel.utils.cell.ButtonName;
import presentation.hotel.utils.cell.CheckButtonCell;
import vo.order.OrderVo;

public class OrderBrowseController implements Initializable{

	private HotelCenterController controller;
	
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
	private TableView<CheckOrderModel> orderTable;
	
	@FXML
	private TableColumn<CheckOrderModel, String> userName;
	
	@FXML
    private TableColumn<CheckOrderModel, String> checkInTime;
	
	@FXML
	private TableColumn<CheckOrderModel,String> status;
	
	@FXML
	private TableColumn<CheckOrderModel,String> userId;
	
	@FXML
	private TableColumn<CheckOrderModel,String> orderId;
	
	@FXML
	private TableColumn<CheckOrderModel,String> totalPrice;
	
	@FXML
	private TableColumn<CheckOrderModel,ButtonName> operation;

	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderTable.setEditable(false);
	}
	
	@FXML
	public void handleQuery(){
		test();
	}
	
	public void test(){
		ObservableList<CheckOrderModel> orderData = FXCollections.observableArrayList();
    	orderData.add(new CheckOrderModel("小手表","233","101","2016-10-09","异常","290元"));
		orderData.add(new CheckOrderModel("小手表","233","102","2016-10-12","已执行","1000元"));
		orderData.add(new CheckOrderModel("小手表","233","103","2016-10-15","未执行","400元"));
		orderData.add(new CheckOrderModel("小手表","233","104","2016-10-30","已执行","2950元"));
		orderTable.setItems(orderData);
		
		userName.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
		userId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
		orderId.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CheckOrderModel, ButtonName>, 
                ObservableValue<ButtonName>>() {

            public ObservableValue<ButtonName> call(TableColumn.CellDataFeatures<CheckOrderModel, ButtonName> p) {
            	return new SimpleObjectProperty<ButtonName>(p.getValue().getOperation());
            }
        });
		

		operation.setCellFactory(
                new Callback<TableColumn<CheckOrderModel, ButtonName>, TableCell<CheckOrderModel, ButtonName>>() {

            public TableCell<CheckOrderModel, ButtonName> call(TableColumn<CheckOrderModel, ButtonName> p) {
                return new CheckButtonCell(ButtonName.CHECK);
            }    
        });
	}

}
