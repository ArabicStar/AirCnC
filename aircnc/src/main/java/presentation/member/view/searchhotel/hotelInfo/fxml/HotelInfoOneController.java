package presentation.member.view.searchhotel.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import presentation.member.model.SearchHotelsModel;

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
	
	private HotelInfoMainController controller;
	
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
	public void handlePage2(){
		controller.addHotelInfoTwoPane();
	}
	
	@FXML
	public void handlePage3(){
		controller.addHotelInfoFourPane();
	}
	
	public void setInfoMainController(HotelInfoMainController controller){
		this.controller=controller;
	}
	
	public void setModel(SearchHotelsModel model){
		this.model = model;
	}
	
	private void initHotelInfo(){
		String style = "-fx-background-color: #fff;-fx-border-color: #585697;-fx-padding:5px 10px 5px 10px;-fx-text-fill: #fff; -fx-font-size: 20px; -fx-border-radius: 10;-fx-text-fill:#585697; ";
		//model = manager
		this.scope.setText(model.getHotelScope());
		this.location.setText(model.getHotelLocation());
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
		rating.setMax(Integer.valueOf(model.getStar()));
		rating.setRating(Double.valueOf(model.getStar()));
		rating.setDisable(true);
		this.star.getChildren().add(rating);
		
		
	}
	
}
