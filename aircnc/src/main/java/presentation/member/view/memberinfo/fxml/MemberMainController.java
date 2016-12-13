package presentation.member.view.memberinfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.member.ClientCenterController;

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
		controller.addInfoMainPane();
		memberInfo.setDisable(true);
		searchHotel.setDisable(false);
		myOrder.setDisable(false);
		creditChange.setDisable(false);
	}
	
	@FXML
	private void HandleSearchHotel(){
		controller.addSearchHotelPane();
		memberInfo.setDisable(false);
		searchHotel.setDisable(true);
		myOrder.setDisable(false);
		creditChange.setDisable(false);
	}
	
	@FXML
	private void HandleMyOrder(){
		controller.addOrderMainPane();
		memberInfo.setDisable(false);
		searchHotel.setDisable(false);
		myOrder.setDisable(true);
		creditChange.setDisable(false);
	}
	
	@FXML
	private void HandleCreditChange(){
		controller.addCreditChangePane();
		memberInfo.setDisable(false);
		searchHotel.setDisable(false);
		myOrder.setDisable(false);
		creditChange.setDisable(true);
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
