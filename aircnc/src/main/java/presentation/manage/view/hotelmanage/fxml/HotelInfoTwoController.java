package presentation.manage.view.hotelmanage.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.manage.manager.HotelManagePromotionManager;
import presentation.manage.manager.impl.HotelManagePromotionManagerImpl;

public class HotelInfoTwoController implements Initializable{

	@FXML
	public Label description;
	
	private HotelManagePromotionManager manager;
	
	private List<String> descriptionList;
	
	private HotelInfoMainController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(!HotelManagePromotionManagerImpl.isLaunched()){
			HotelManagePromotionManagerImpl.launch();
		}
		manager = HotelManagePromotionManagerImpl.getInstance();
		
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
		controller.addHotelInfoFourPane();
	}
	
	public void setInfoMainController(HotelInfoMainController controller){
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
