package presentation.member.view.creditchange.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;
import presentation.member.view.creditchange.CreditModel;
import presentation.member.view.myorder.MyorderModel;

/**
 * the controller of 
 * @author paranoia
 *
 */
public class MemberCreditChangeController implements Initializable{
	
	private ClientCenterController controller;
	
	@FXML
	private TableView<CreditModel> creditTable;
	
	@FXML
	private TableColumn<MyorderModel, String> date;
	
	@FXML
    private TableColumn<MyorderModel, Boolean> symbol;
	
	@FXML
	private TableColumn<MyorderModel,String> time;
	
	@FXML
	private TableColumn<MyorderModel,String> description;
	
	@FXML
	private TableColumn<MyorderModel,Integer> change;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
