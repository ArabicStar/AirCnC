package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import presentation.member.ClientCenterController;
import javafx.scene.input.MouseEvent; 

/**
 * the controller of member main pane.
 * @author paranoia
 *
 */
public class MemberMainController implements Initializable{
	
	@FXML
	private Label memberInfo;
	
	@FXML
	private Label searchHotel;
	
	@FXML
	private Label myOrder;
	
	@FXML
	private Label creditChange;
	
	private ClientCenterController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	@FXML
	private void HandleMemberInfo(){
		memberInfo.setText("你蠢不蠢");
		controller.addInfoMainPane();
	}
	
	@FXML
	private void HandleSearchHotel(){
		searchHotel.setText("Boooooom");
		controller.addSearchHotelPane();
	}
	
	@FXML
	private void HandleMyOrder(){
		myOrder.setText("哈哈哈哈");
		controller.addOrderMainPane();
	}
	
	@FXML
	private void HandleCreditChange(){
		creditChange.setText("新世界");
		controller.addCreditChangePane();
	}
	
	/**
	 * set the centerController
	 * @param centerController
	 */
	public void setCenterController(ClientCenterController centerController) {
		// TODO Auto-generated method stub
		this.controller=centerController;
	}
}
