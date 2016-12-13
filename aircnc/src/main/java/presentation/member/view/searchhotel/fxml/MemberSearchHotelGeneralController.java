package presentation.member.view.searchhotel.fxml;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import presentation.member.ClientCenterController;
import presentation.member.manager.SearchHotelManager;
import presentation.member.model.SearchHotelsModel;

/**
 * the controller of hotel general info.
 * @author paranoia
 *
 */
public class MemberSearchHotelGeneralController implements Initializable{
	
	@FXML
	private Label hotelName;
	
	@FXML
	private Label grade;
	
	@FXML
	private Label scope;
	
	@FXML
	private Label location;
	
	@FXML
	private HBox promotion;
	
	@FXML
	private Label lowestPrice;
	
	@FXML 
	private Button reserve;
	
	@FXML
	private Label execute;
	
	@FXML
	private Label unexecute;
	
	@FXML
	private Label abnormal;
	
	@FXML
	private Label repeal;
	
	private SearchHotelManager manager;
	private ClientCenterController controller;
	
	private SearchHotelsModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initHotelInfo();
			  }
		});	
	}
	
	@FXML
	public void handleReverse(){
		
	}
	
	public void initHotelInfo(){
		
		hotelName.setText(model.getHotelName());
		grade.setText(String.valueOf(model.getHotelGrade()));
		scope.setText(model.getHotelScope());
		location.setText(model.getHotelLocation());
		//promotion
		lowestPrice.setText(String.valueOf(model.getLowestPrice()));
		execute.setText(String.valueOf(model.getExecuteOrderNum()));
		unexecute.setText(String.valueOf(model.getUnexecuteOrderNum()));
		abnormal.setText(String.valueOf(model.getAbnormalOrderNum()));
		repeal.setText(String.valueOf(model.getRepealOrderNum()));
	}
	
	/**
	 * set the main controller
	 * @param controller
	 */
	public void setCenterController(ClientCenterController controller){
		this.controller = controller;
	}
	
	/**
	 * set the main manager
	 * @param controller
	 */
	public void setManager(SearchHotelManager manager){
		this.manager = manager;
	}
}
