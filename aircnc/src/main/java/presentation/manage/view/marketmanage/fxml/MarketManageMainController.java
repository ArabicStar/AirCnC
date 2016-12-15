package presentation.manage.view.marketmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import presentation.manage.model.MarketManageModel;

/**
 * the controller of market manage.
 * @author paranoia
 *
 */
public class MarketManageMainController implements Initializable{

	@FXML
	private TextField marketId;
	
	@FXML
	private TableColumn<MarketManageModel, String> marketName;
	
	@FXML
    private TableColumn<MarketManageModel, String> id;
	
	@FXML
	private TableColumn<MarketManageModel,Boolean> operation;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}

