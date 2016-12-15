package presentation.member.view.searchhotel.fxml;


import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import presentation.member.model.SearchHotelsModel;

/**
 * the controller of hotel general info.
 * @author paranoia
 *
 */
public class MemberSearchHotelGeneralController implements Initializable{
	
	@FXML
	private ImageView portrait;
	
	@FXML
	private Label hotelName;
	
	@FXML
	private Label grade;
	
	@FXML
	private Label scope;
	
	@FXML
	private Label location;
	
	@FXML
	private Label promotion;
	
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
	
	private MemberSearchHotelController controller;
	
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
	
	/**
	 * initialize the hotel information
	 * @param portrait
	 * @param hotelName
	 * @param grade
	 * @param location
	 * @param lowestPrice
	 * @param orders
	 */
	public void initHotelInfo(){
		//model = manager.getHotelList();
		URL loc = getClass().getResource("/images/member/hotel-"+model.getStar()+".png");
		Image image = new Image(loc.toString()); 
		portrait.setImage(image);
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
	public void setController(MemberSearchHotelController controller){
		this.controller = controller;
	}
	
	/**
	 * this model contains all the information presented.
	 * @param model
	 */
	public void setHotelModel(SearchHotelsModel model){
		this.model = model;
	}
}
