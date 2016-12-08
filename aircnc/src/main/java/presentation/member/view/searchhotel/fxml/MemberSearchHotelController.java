package presentation.member.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.member.ClientCenterController;
import presentation.member.manager.SearchHotelManager;

/**
 * the controller of hotel search (main).
 * @author paranoia
 *
 */
public class MemberSearchHotelController implements Initializable{
	
	/*
	 * 记录点东西：酒店框长720 宽130 第一个坐标30，110 ，往下依次y坐标加150
	 */
	
	@FXML
	private TextField hotelName;
	
	@FXML
	private Label supreme;
	
	private ClientCenterController controller;
	private SearchHotelManager manager;
	
	public void setCenterController(ClientCenterController controller){
		this.controller=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleSupreme(){
		
	}
	
	public void setManager(SearchHotelManager manager){
		this.manager = manager;
	}
	
}
