package presentation.manage.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.manage.CenterController;

public class ManageMainController implements Initializable{
	
	@FXML
	private Label memberManage;
	
	@FXML
	private Label hotelManage;
	
	@FXML
	private Label marketManage;
	
	private CenterController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleMemberManage(){
		memberManage.setDisable(true);
		hotelManage.setDisable(false);
		marketManage.setDisable(false);
		controller.addMemberManagePane();
	}
	
	@FXML
	public void handleHotelManage(){
		memberManage.setDisable(false);
		hotelManage.setDisable(true);
		marketManage.setDisable(false);
		controller.addHotelManagePane();
	}
	
	@FXML
	public void handleMarketManage(){
		memberManage.setDisable(false);
		hotelManage.setDisable(false);
		marketManage.setDisable(true);
		controller.addMarketManagePane();
	}
	
	public void setMemberManageDisable(){
		memberManage.setDisable(true);
	}
	
	@FXML
	public void handleLogout(){
		
	}
	
	public void setCenterController(CenterController controller){
		this.controller = controller;
	}

}
