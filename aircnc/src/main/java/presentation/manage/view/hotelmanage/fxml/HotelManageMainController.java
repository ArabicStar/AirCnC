package presentation.manage.view.hotelmanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import presentation.manage.model.HotelManageModel;
import presentation.manage.model.MemberManageModel;

/**
 * the controller of market manage.
 * @author paranoia
 *
 */
public class HotelManageMainController implements Initializable{

	@FXML
	private TextField hotelId;
	
	@FXML
	private TableColumn<HotelManageModel, String> hotelName;
	
	@FXML
    private TableColumn<HotelManageModel, String> id;
	
	@FXML
	private TableColumn<HotelManageModel,Boolean> operation;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}