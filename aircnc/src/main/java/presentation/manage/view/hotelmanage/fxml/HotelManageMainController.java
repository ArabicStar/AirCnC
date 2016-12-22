package presentation.manage.view.hotelmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import presentation.manage.CenterController;
import presentation.manage.ManageTest;
import presentation.manage.accessor.HotelManageInfoAccessor;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.manager.HotelManageInfoManager;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.model.HotelManageModel;
import presentation.manage.utils.cell.HotelManageButtonCell;
import presentation.manage.utils.dialog.PlainDialog;
import presentation.manage.view.hotelmanage.HotelAddPane;
import presentation.manage.view.hotelmanage.HotelInfoMainPane;
import vo.hotel.HotelVo;

/**
 * the controller of market manage.
 * @author paranoia
 *
 */
public class HotelManageMainController implements Initializable{

	@FXML
	private TextField hotelId;
	
	@FXML
	private Button search;
	
	@FXML
	private TableView<HotelManageModel> hotelTable;
	
	@FXML
	private TableColumn<HotelManageModel, String> hotelName;
	
	@FXML
    private TableColumn<HotelManageModel, String> id;
	
	@FXML
	private TableColumn<HotelManageModel,HotelVo> operation;
	
	private HotelManageInfoAccessor accessor;
	private HotelManageInfoManager manager;
	private ObservableList<HotelManageModel> models;
	private HotelManageMainController hotController = this;
	
	private HotelInfoMainPane detailedInfo;
	private HotelAddPane addPane;
	private HotelManageModel model;
	private AnchorPane rootLayout;
	
	@SuppressWarnings("unused")
	private CenterController centerController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hotelTable.setEditable(false);
		accessor = HotelManageInfoAccessorImpl.getInstance();
		manager = HotelManageInfoManagerImpl.getInstance();
	}
	
	@FXML
	public void handleQuery(){
		if(hotelId.getText().length()>0){
			ManageTest.getHotelData();
			accessor.setId(hotelId.getText());
			models = manager.getHotelInfoList();
			model = models.get(0);
			hotelTable.setItems(models);
			hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
			id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
			
			operation.setSortable(false);
			
			operation.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<HotelManageModel, HotelVo>, 
	                ObservableValue<HotelVo>>() {

	            public ObservableValue<HotelVo> call(TableColumn.CellDataFeatures<HotelManageModel, HotelVo> p) {
	            	return new SimpleObjectProperty<HotelVo>(p.getValue().getOperation());
	            }
	        });
		

			operation.setCellFactory(
	                new Callback<TableColumn<HotelManageModel,HotelVo>, TableCell<HotelManageModel, HotelVo>>() {
	            public TableCell<HotelManageModel,HotelVo> call(TableColumn<HotelManageModel, HotelVo> p) {
	                return new HotelManageButtonCell(hotController);
	            }       
	        });
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,"搜索失败","请输入搜索的ID");
			alert.showDialog();
		}
	}
	
	public void setHotelModel(HotelManageModel model){
		this.model = model;
	}
	
	public void setRootLayout(AnchorPane pane){
		this.rootLayout = pane;
	}
	
	@FXML
	public void handleAddHotel(){
		addPane = new HotelAddPane();
		rootLayout.getChildren().add(addPane.getPane());
		AnchorPane.setTopAnchor(addPane.getPane(), 200.0);
		AnchorPane.setLeftAnchor(addPane.getPane(), 80.0);
		addPane.getController().setController(this);
	}
	
	public void removeAddHotel(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
	@FXML
	public void handleDetailedInfo(){
		detailedInfo = new HotelInfoMainPane(model);
		rootLayout.getChildren().add(detailedInfo.getAnchorPane());
		AnchorPane.setTopAnchor(detailedInfo.getAnchorPane(), 0.0);
		detailedInfo.getController().setController(this);
	}
	
	public void removeDetailedInfo(){
		rootLayout.getChildren().remove(rootLayout.getChildren().size()-1);
	}
	
	public void setCenterController(CenterController controller){
		this.centerController = controller;
	}
	
	
} 

