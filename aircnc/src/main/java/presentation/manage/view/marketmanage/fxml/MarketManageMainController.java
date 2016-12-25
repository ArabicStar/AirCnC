package presentation.manage.view.marketmanage.fxml;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import interactor.impl.manage.ManageMarketCourier;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import presentation.manage.CenterController;
import presentation.manage.accessor.MarketManageInfoAccessor;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.manager.MarketManageInfoManager;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.model.MarketManageModel;
import presentation.manage.utils.cell.MarketManageButtonCell;
import presentation.manage.utils.dialog.MarketModifyDialog;
import presentation.manage.utils.dialog.PlainDialog;
import presentation.manage.view.marketmanage.MarketAddPane;
import vo.market.MarketVo;

/**
 * the controller of market manage.
 * 
 * @author paranoia
 *
 */
public class MarketManageMainController implements Initializable {

	@FXML
	private TextField marketId;

	@FXML
	private Button search;

	@FXML
	private TableView<MarketManageModel> marketTable;

	@FXML
	private TableColumn<MarketManageModel, String> marketName;

	@FXML
	private TableColumn<MarketManageModel, String> id;

	@FXML
	private TableColumn<MarketManageModel, MarketVo> operation;

	private MarketManageInfoAccessor accessor;
	private MarketManageInfoManager manager;
	private ObservableList<MarketManageModel> models;
	private MarketManageMainController marController = this;

	private MarketAddPane addPane;
	private AnchorPane rootLayout;

	@SuppressWarnings("unused")
	private CenterController centerController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		marketId.setPromptText("营销人员ID");
		marketTable.setEditable(false);
		accessor = MarketManageInfoAccessorImpl.getInstance();
		manager = MarketManageInfoManagerImpl.getInstance();

	}

	@FXML
	public void handleQuery() {
		if (marketId.getText().length() > 0) {
			if (checkId(marketId.getText())) {
				accessor.setId(marketId.getText());
				boolean valid = ManageMarketCourier.getInstance().getMarketInfo();
				if(valid){
				models = manager.getMarketInfoList();

				marketTable.setItems(models);
				marketName.setCellValueFactory(cellData -> cellData.getValue().marketNameProperty());
				id.setCellValueFactory(cellData -> cellData.getValue().idProperty());

				operation.setSortable(false);

				operation.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<MarketManageModel, MarketVo>, ObservableValue<MarketVo>>() {

							public ObservableValue<MarketVo> call(
									TableColumn.CellDataFeatures<MarketManageModel, MarketVo> p) {
								return new SimpleObjectProperty<MarketVo>(p.getValue().getOperation());
							}
						});

				operation.setCellFactory(
						new Callback<TableColumn<MarketManageModel, MarketVo>, TableCell<MarketManageModel, MarketVo>>() {
							public TableCell<MarketManageModel, MarketVo> call(
									TableColumn<MarketManageModel, MarketVo> p) {
								return new MarketManageButtonCell(marController);
							}
						});
				}else{
					PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "搜索失败", "未查找到该ID");
					alert.showDialog();
				}
			} else {
				PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "搜索失败", "请输入有效的8位ID");
				alert.showDialog();
			}
		} else {
			PlainDialog alert = new PlainDialog(AlertType.INFORMATION, "搜索失败", "请输入搜索的ID");
			alert.showDialog();
		}

	}

	@FXML
	public void handleAddMarket() {
		addPane = new MarketAddPane();
		rootLayout.getChildren().add(addPane.getPane());
		AnchorPane.setTopAnchor(addPane.getPane(), 200.0);
		AnchorPane.setLeftAnchor(addPane.getPane(), 80.0);
		addPane.getController().setController(this);
	}

	public void removeAddMarket() {
		rootLayout.getChildren().remove(rootLayout.getChildren().size() - 1);
	}

	public void handleModifyMarket(MarketVo vo) {
		@SuppressWarnings("unused")
		MarketModifyDialog memAlert = new MarketModifyDialog(vo);
	}

	public void handleDeleteMarket(MarketVo vo) {
		PlainDialog delete = new PlainDialog(AlertType.CONFIRMATION, "删除营销人员", "确认删除该营销人员吗？");
		Optional<ButtonType> result = delete.showDialog();

		if(result.get() == ButtonType.OK){
			MarketManageInfoAccessorImpl.getInstance().setId(vo.getId());
			ManageMarketCourier.getInstance().deleteMarketInfo();	
		}

	}

	public void setCenterController(CenterController controller) {
		this.centerController = controller;
	}

	public void setRootLayout(AnchorPane pane) {
		this.rootLayout = pane;
	}
	
	private static boolean checkId(String id) {
		return id != null && id.matches("[0-9]{8}");
	}
}
