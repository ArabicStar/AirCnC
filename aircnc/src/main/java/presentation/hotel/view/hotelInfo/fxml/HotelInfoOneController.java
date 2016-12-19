package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;
import presentation.hotel.view.hotelInfo.HotelInfoController;

public class HotelInfoOneController implements Initializable{

	@FXML
	private Label scope;
	
	@FXML
	private Label location;
	
	@FXML
	private Pane star;
	
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
		controller.addHotelInfoThreePane();
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
		String style = "-fx-background-color: #fff;-fx-border-color: #585697;-fx-padding:5px 10px 5px 10px;-fx-text-fill: #fff; -fx-font-size: 20px; -fx-border-radius: 10;-fx-text-fill:#585697; ";
		model = manager.getHotelInfo();
		this.scope.setText(model.getScope());
		this.location.setText(model.getLocation());
		this.roomPrice.setText(model.getRoomPrice());
		this.roomType.setText(model.getRoomName());
		this.introduction.setText(model.getIntro());
		String[] equips = model.getEquip().split(";");
		for(String tmp:equips){
			Label l = new Label(tmp);
			l.setStyle(style);
			equipment.getChildren().add(l);
		}
		
		Rating rating = new Rating();
		rating.setMax(Integer.parseInt(model.getStar()));
		rating.setRating(Double.parseDouble(model.getStar()));
		rating.setDisable(true);;
		this.star.getChildren().add(rating);
		
		
	}
	
}
