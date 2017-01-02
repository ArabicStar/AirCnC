package presentation.member.view.browsehotel.hotelInfo.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import presentation.member.manager.HotelPromotionManager;
import presentation.member.manager.impl.HotelPromotionManagerImpl;
import presentation.member.model.SearchHotelsModel;

public class BrowseInfoTwoController implements Initializable{

	@FXML
	public Label description;
	
	@SuppressWarnings("unused")
	private HotelPromotionManager manager;
	
	private List<String> descriptionList;
	
	private BrowseInfoMainController controller;
	private SearchHotelsModel model;
	
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
	
	@FXML
	public void handlePage4(){
		controller.addHotelInfoFourPane();
	}
	
	public void setInfoMainController(BrowseInfoMainController controller){
		this.controller=controller;
	}
	
	private void initPromotion(){
		descriptionList = model.getPromotion();
		String content = "";
		int i = 0;
		for(String s:descriptionList){
			i++;
			content = content+"\n"+Integer.toString(i)+". "+s;
		}
		description.setText(content);
	}
	
	public void setModel(SearchHotelsModel model){
		this.model = model;
	}
	
	@FXML
	public void handleReverse(){
		controller.handleReverse();
	}

}
