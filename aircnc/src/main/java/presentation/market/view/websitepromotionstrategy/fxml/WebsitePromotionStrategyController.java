package presentation.market.view.websitepromotionstrategy.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import presentation.market.MarketCenterController;
import presentation.market.model.PromotionModel;
import presentation.market.utils.ButtonName;
import presentation.market.utils.PromotionButtonCell;

public class WebsitePromotionStrategyController implements Initializable{

	@SuppressWarnings("unused")
	private MarketCenterController controller;
	
	@FXML
	private TableView<PromotionModel> promotionTable;
	
	@FXML
	private TableColumn<PromotionModel, String> description;
	
	@FXML 
	private TableColumn<PromotionModel, ButtonName> operation;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}
	
	public void test() {
		ObservableList<PromotionModel> promotionData = FXCollections.observableArrayList();
		promotionData.add(new PromotionModel("满300返100"));
		promotionData.add(new PromotionModel("一律七折"));
		promotionData.add(new PromotionModel("唱小星星可以减50元"));
		promotionData.add(new PromotionModel("生日当天减100元"));
		promotionData.add(new PromotionModel("送一份冰淇淋"));
		promotionTable.setItems(promotionData);
		description.setCellValueFactory(cellData -> cellData.getValue().getPromotionDescription());
		
		operation.setSortable(false);
		
		operation.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PromotionModel,ButtonName>, 
				ObservableValue<ButtonName>>() {
			
			@Override
			public ObservableValue<ButtonName> call(CellDataFeatures<PromotionModel, ButtonName> p) {
				return new SimpleObjectProperty<ButtonName>(p.getValue().getOperation());
			}
		});
		
		operation.setCellFactory(
				new Callback<TableColumn<PromotionModel, ButtonName>, 
				TableCell<PromotionModel,ButtonName>>() {
			public TableCell<PromotionModel, ButtonName> call(TableColumn<PromotionModel, ButtonName> p) {
				return new PromotionButtonCell(ButtonName.EDIT);
			}
		});
	}
	

}
