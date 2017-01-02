package presentation.member.view.browsehotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.member.ClientCenterController;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import presentation.member.model.SearchHotelsModel;
import presentation.member.utils.cell.BrowseCell;
import vo.hotel.HotelVo;

public class BrowseHotelsController implements Initializable{
	
	@FXML
	private TableView<SearchHotelsModel> hotelTable;

	@FXML
	private TableColumn<SearchHotelsModel, String> hotelName;

	@FXML
	private TableColumn<SearchHotelsModel, String> lowestPrice;

	@FXML
	private TableColumn<SearchHotelsModel, String> star;

	@FXML
	private TableColumn<SearchHotelsModel, String> grade;

	@FXML
	private TableColumn<SearchHotelsModel, String> whetherReverse;

	@FXML
	private TableColumn<SearchHotelsModel, HotelVo> operation;
	
	private ClientCenterController controller;
	private ObservableList<SearchHotelsModel> models;
	private SearchHotelManager manager;
	private BrowseHotelsController browseController = this;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = SearchHotelManagerImpl.getInstance();
		models = manager.getWholeHotelList();
		Platform.runLater(()->{
			hotelTable.setItems(models);
			
			hotelName.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
			lowestPrice.setCellValueFactory(cellData -> cellData.getValue().stringLowPriceProperty());
			star.setCellValueFactory(cellData -> cellData.getValue().stringStarProperty());
			grade.setCellValueFactory(cellData -> cellData.getValue().stringGradeProperty());
			whetherReverse.setCellValueFactory(cellData -> cellData.getValue().reverseProperty());

			operation.setSortable(false);

			operation.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<SearchHotelsModel, HotelVo>, ObservableValue<HotelVo>>() {

						public ObservableValue<HotelVo> call(TableColumn.CellDataFeatures<SearchHotelsModel, HotelVo> p) {
							return new SimpleObjectProperty<HotelVo>(p.getValue().getOperation());
						}
					});

			operation.setCellFactory(
					new Callback<TableColumn<SearchHotelsModel, HotelVo>, TableCell<SearchHotelsModel, HotelVo>>() {
						public TableCell<SearchHotelsModel, HotelVo> call(TableColumn<SearchHotelsModel, HotelVo> p) {
							return new BrowseCell(browseController);
						}
					});
			});
	}
	
	public void setCenterController(ClientCenterController controller){
		this.controller = controller;;
	}
	
	public void handleReverse(){
		
	}
	
	public void removeDetailedInfo(){
		
	}

}
