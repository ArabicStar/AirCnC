package presentation.market.view.websitepromotionstrategy.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import presentation.market.MarketCenterController;
import presentation.market.model.PromotionModel;
import presentation.market.utils.ButtonName;

public class WebsitePromotionStrategyController implements Initializable{

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
		promotionData.add(new PromotionModel("1235326u4596476hfiuhjihfiufhiwuoqhfoewqhofhwioeqhhi富含温泉哦好服务器而范围"));
		promotionTable.setItems(promotionData);
		description.setCellValueFactory(cellData -> cellData.getValue().getPromotionDescription());
	}
	

}
