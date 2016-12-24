package presentation.manage.view.membermanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.manage.CenterController;
import presentation.manage.ManageTest;
import presentation.manage.accessor.MemberManageInfoAccessor;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.manager.MemberManageInfoManager;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import presentation.manage.model.MemberManageModel;
import presentation.manage.utils.cell.MemberManageButtonCell;
import presentation.manage.utils.dialog.PlainDialog;
import presentation.manage.view.membermanage.MemberInfoPane;
import vo.member.MemberVo;

/**
 * the controller of member manage.
 * @author paranoia
 *
 */
public class MemberManageMainController implements Initializable{

	@FXML
	private TextField userId;
	
	@FXML
	private Button search;
	
	@FXML
	private TableView<MemberManageModel> memberTable;
	
	@FXML
	private TableColumn<MemberManageModel, String> username;
	
	@FXML
    private TableColumn<MemberManageModel, String> id;
	
	@FXML
	private TableColumn<MemberManageModel,MemberVo> operation;
	
	private MemberManageInfoAccessor accessor;
	private MemberManageInfoManager manager;
	private ObservableList<MemberManageModel> models;
	private MemberManageMainController memController = this;
	
	private AnchorPane rootLayout;
	
	@SuppressWarnings("unused")
	private CenterController centerController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberTable.setEditable(false);
		accessor = MemberManageInfoAccessorImpl.getInstance();
		manager = MemberManageInfoImpl.getInstance();
	}
	
	@FXML
	public void handleQuery(){
		if(userId.getText().length()>0){
			accessor.setId(userId.getText());
			models = manager.getMemberInfoList();
			
			memberTable.setItems(models);
			username.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
			id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
			
			operation.setSortable(false);
			
			operation.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<MemberManageModel, MemberVo>, 
	                ObservableValue<MemberVo>>() {

	            public ObservableValue<MemberVo> call(TableColumn.CellDataFeatures<MemberManageModel, MemberVo> p) {
	            	return new SimpleObjectProperty<MemberVo>(p.getValue().getOperation());
	            }
	        });
		

			operation.setCellFactory(
	                new Callback<TableColumn<MemberManageModel,MemberVo>, TableCell<MemberManageModel, MemberVo>>() {
	            public TableCell<MemberManageModel,MemberVo> call(TableColumn<MemberManageModel, MemberVo> p) {
	                return new MemberManageButtonCell(memController);
	            }       
	        });
		}else{
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION,"搜索失败","请输入搜索的ID");
			alert.showDialog();
		}
	}
	
	public void setRootLayout(AnchorPane root){
		this.rootLayout = root;
	}
	
	public void addInfoPane(MemberManageModel model){
		//this.rootLayout.getChildren().clear();
		MemberInfoPane pane = new MemberInfoPane(model);
		this.rootLayout.getChildren().add(pane.getContentPane());
		AnchorPane.setTopAnchor(pane.getContentPane(), 0.0);
		pane.getController().setController(this);
	}
	
	public void removeInfoPane(){
		this.rootLayout.getChildren().remove(this.rootLayout.getChildren().size()-1);
	}
	
	public void setCenterController(CenterController controller){
		this.centerController = controller;
	}
	
	
}
