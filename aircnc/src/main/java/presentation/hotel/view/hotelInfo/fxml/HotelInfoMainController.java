package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import interactor.hotel.HotelInfoInteractor;
import interactor.impl.hotel.HotelInfoCourier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.hotel.HotelCenterController;
import presentation.hotel.manager.InfoManager;
import presentation.hotel.manager.impl.InfoManagerImpl;
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
	
	private HotelInfoInteractor interactor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		manager = InfoManagerImpl.getInstance();
		
		interactor = HotelInfoCourier.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initHotelInfo();
			  }
		});
		
	}
	
	public void setCenterController(HotelCenterController controller){
		this.controller=controller;
	}
	
	private void initHotelInfo(){
		interactor.getHotelInfo();
		model = manager.getHotelInfo();
		id.setText(model.getId());
		name.setText(model.getName());
		grade.setText(model.getGrade());
		hotelImage.setImage(new Image(this.getClass().getResource("")+"../../../../../images/hotel/star/hotel-"+model.getStar()+".png"));
		
	}
	
	
}
