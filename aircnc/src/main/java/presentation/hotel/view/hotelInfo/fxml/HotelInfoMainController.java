package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.hotel.HotelCenterController;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.model.HotelInfoModel;

public class HotelInfoMainController implements Initializable{
	@FXML
	private Label grade;
	
	@FXML
	private Label name;
	
	@FXML
	private Label id;
	
	@FXML
	private ImageView hotelImage;
	
	private HotelCenterController controller;
	
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
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	public InfoManager getManager(){
		return manager;
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
		model = manager.getHotelInfo();
		id.setText(model.getId());
		name.setText(model.getName());
		grade.setText(model.getGrade());
		hotelImage.setImage(new Image(this.getClass().getResource("")+"../../../../../images/hotel/star/hotel-"+model.getStar()+".png"));
		
	}
	
	
}
