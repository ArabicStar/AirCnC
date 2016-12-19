package presentation.manage.view.marketmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import presentation.manage.CenterController;
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
	private Button search;
	
	@FXML
	private TableView<MarketManageModel> marketTable;
	
	@FXML
	private TableColumn<MarketManageModel, String> marketName;
	
	@FXML
    private TableColumn<MarketManageModel, String> id;
	
	@FXML
	private TableColumn<MarketManageModel,Boolean> operation;
	
	@SuppressWarnings("unused")
	private CenterController centerController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(CenterController controller){
		this.centerController = controller;
	}
	
	
}

