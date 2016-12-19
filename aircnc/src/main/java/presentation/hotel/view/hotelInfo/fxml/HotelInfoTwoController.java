package presentation.hotel.view.hotelInfo.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.hotel.HotelCenterController;
import presentation.hotel.manager.HotelPromotionManager;
import presentation.hotel.manager.impl.HotelPromotionManagerImpl;
import presentation.hotel.view.hotelInfo.HotelInfoController;

public class HotelInfoTwoController implements Initializable{

	@FXML
	public Label description;
	
	private HotelPromotionManager manager;
	
	private List<String> descriptionList;
	
	private HotelInfoController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(!HotelPromotionManagerImpl.isLaunched()){
			HotelPromotionManagerImpl.launch();
		}
		manager = HotelPromotionManagerImpl.getInstance();
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initPromotion();
			  }
		});
	}
	
	@FXML
	public void handlePage1(){
		controller.addHotelInfoOnePane();
	}
	
	@FXML
	public void handlePage3(){
		controller.addHotelInfoThreePane();
	}
	
	public void setInfoMainController(HotelInfoController controller){
		this.controller=controller;
	}
	
	private void initPromotion(){
		descriptionList = manager.getDescription();
		String content = "";
		int i = 0;
		for(String s:descriptionList){
			i++;
			content = content+"\n"+Integer.toString(i)+". "+s;
		}
		description.setText(content);
	}

}
