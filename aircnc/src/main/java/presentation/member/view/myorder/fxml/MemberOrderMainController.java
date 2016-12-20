package presentation.member.view.myorder.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import interactor.impl.member.MemberInfoCourier;
import interactor.impl.order.OrderInfoCourier;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.accessor.SearchOrderInfoAccessor;
import presentation.member.accessor.impl.MemberAppealAccessorImpl;
import presentation.member.accessor.impl.MemberCommentAccessorImpl;
import presentation.member.accessor.impl.MemberOrderOperationAccessorImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.model.MyOrderModel;
import presentation.member.utils.cell.FunctionButtons;
import presentation.member.utils.dialog.PlainDialog;
import presentation.member.view.myorder.MemberAppealPane;
import presentation.member.view.myorder.MemberCommentPane;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class MemberOrderMainController implements Initializable{
	
	@SuppressWarnings("unused")
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
	
	@FXML
	private TableColumn<MyOrderModel,OrderVo> operation;
	
	private MyOrderManager manager;
	private SearchOrderInfoAccessor accessor;
	private AnchorPane rootLayout;
	private MemberOrderMainController OrderController = this;
	private ObservableList<MyOrderModel> models;
	private Set<OrderStatus> states;
	
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
		accessor = SearchOrderInfoAccessorImpl.getInstance();
	}

	/**
	 * 1.deliver the search requirement to the logic layer.
	 * 2.get the searched results.
	 * 3.add the content to the tableview
	 */
	@FXML
	public void handleQuery(){
		
		states = new HashSet<OrderStatus>();		
		if(finished.isSelected()){
			states.add(OrderStatus.EXECUTED);
			states.add(OrderStatus.REVIEWED);
		}
		
		if(unfinished.isSelected()){
			states.add(OrderStatus.UNEXECUTED);
		}
		
		if(exception.isSelected()){
			states.add(OrderStatus.ABNORMAL);
			states.add(OrderStatus.APPEALING);
		}
		
		if(cancelled.isSelected()){
			states.add(OrderStatus.REPEALED);
		}
		
		accessor.setSearchTarget(states);
		
		MemberInfoCourier.getInstance().getMemberOrdersByStatus();
		
		models = manager.getOrderList();
		orderTable.setItems(models);		
		
		hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
		checkInTime.setCellValueFactory(cellData -> cellData.getValue().checkInTimeProperty());
		state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
		timeAndSum.setCellValueFactory(cellData -> cellData.getValue().timeAndSumProperty());
		totalPrice.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty());
		
		operation.setSortable(false);
		
		operation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<MyOrderModel, OrderVo>, 
                ObservableValue<OrderVo>>() {

            public ObservableValue<OrderVo> call(TableColumn.CellDataFeatures<MyOrderModel, OrderVo> p) {
            	return new SimpleObjectProperty<OrderVo>(p.getValue().getOperation());
            }
        });
	

		operation.setCellFactory(
                new Callback<TableColumn<MyOrderModel,OrderVo>, TableCell<MyOrderModel, OrderVo>>() {
            public TableCell<MyOrderModel,OrderVo> call(TableColumn<MyOrderModel, OrderVo> p) {
                return new FunctionButtons(OrderController);
            }       
        });
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	public void addCommentPane(OrderVo vo){
		if(!MemberCommentAccessorImpl.isLaunched())
        	MemberCommentAccessorImpl.launch();
		MemberCommentPane comment = new MemberCommentPane(vo);
		rootLayout.getChildren().add(comment.getPane());
		AnchorPane.setTopAnchor(comment.getPane(), 150.0);
		AnchorPane.setLeftAnchor(comment.getPane(), 50.0);
		comment.getController().setController(this);
	}
	
	public void removeCommentPane(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
	public void addAppealPane(OrderVo vo){
		if(!MemberAppealAccessorImpl.isLaunched())
        	MemberAppealAccessorImpl.launch();
		MemberAppealPane appeal = new MemberAppealPane(vo);
		rootLayout.getChildren().add(appeal.getPane());
		AnchorPane.setTopAnchor(appeal.getPane(), 150.0);
		AnchorPane.setLeftAnchor(appeal.getPane(), 50.0);
		appeal.getController().setController(this);
	}
	
	public void removeAppealPane(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
	public void refresh(){
		accessor.setSearchTarget(states);
		MemberInfoCourier.getInstance().getMemberOrdersByStatus();
		models = manager.getOrderList();
	}
	
	public void cancelOrder(OrderVo vo){
		PlainDialog alert3 = new PlainDialog(AlertType.INFORMATION,
       			"取消订单","你确定取消该订单吗？");
		Optional<ButtonType> result = alert3.showDialog();
       		result.ifPresent( ok -> {
    				MemberOrderOperationAccessorImpl.getInstance().setCancel(vo.getOrderId());
    				OrderInfoCourier.getInstance().repeal();
    			});
	}
	
}
