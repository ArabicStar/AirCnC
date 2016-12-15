package presentation.manage.view.membermanage.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import presentation.manage.model.MemberManageModel;

/**
 * the controller of member manage.
 * @author paranoia
 *
 */
public class MemberManageMainController implements Initializable{

	@FXML
	private TextField userId;
	
	@FXML
	private TableColumn<MemberManageModel, String> username;
	
	@FXML
    private TableColumn<MemberManageModel, String> id;
	
	@FXML
	private TableColumn<MemberManageModel,Boolean> operation;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
