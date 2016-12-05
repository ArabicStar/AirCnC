package presentation.market.view.websitepromotionstrategy.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import presentation.market.MarketCenterController;
import presentation.market.model.PromotionModel;

public class WebsitePromotionStrategyController implements Initializable{

	private MarketCenterController controller;
	
	@FXML
	private TableView<PromotionModel> promotionTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}
	

}
