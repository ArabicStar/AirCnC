package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;
import presentation.hotel.view.hotelInfo.HotelInfoController;

public class HotelInfoOneController implements Initializable{

	@FXML
	private Label scope;
	
	@FXML
	private Label location;
	
	@FXML
	private Label star;
	
	@FXML
	private Label introduction;
	
	@FXML
	private Label roomType;
	
	@FXML
	private Label roomPrice;
	
	@FXML
	private FlowPane equipment;
	
	@FXML
	private Button page2;
	
	@FXML
	private Button page3;
	
	@FXML
	private Button modify;
	
	private HotelInfoController controller;
	
	private InfoManager manager;
	
	private HotelInfoModel model;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initHotelInfo();
			  }
		});
		
	}
	
	@FXML
	public void handleModify(){
		controller.addHotelModifyPane();
	}
	
	@FXML
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	@FXML
	public void handlePage3(){
		
	}
	
	public void setInfoMainController(HotelInfoController controller){
		this.controller=controller;
	}
	
	/**
	 * set the hotel info manager
	 * aiming to fetch the hotel info model
	 * @param manager
	 */
	public void setManager(InfoManager manager){
		this.manager = manager;
	}
	
	private void initHotelInfo(){
		String style = "-fx-background-color: #585697;-fx-text-fill: #fff; -fx-font-size: 10pt; -fx-border-radius: 5; -fx-background-radius: 20";
		model = manager.getHotelInfo();
		this.scope.setText(model.getScope());
		this.location.setText(model.getLocation());
		this.star.setText(model.getStar());
		this.roomPrice.setText(model.getRoomPrice());
		this.roomType.setText(model.getRoomName());
		this.introduction.setText(model.getIntro());
		String[] equips = model.getEquip().split(";");
		for(String tmp:equips){
			Label l = new Label(tmp);
			l.setStyle(style);
			equipment.getChildren().add(l);
		}
		
		
	}
	
}
