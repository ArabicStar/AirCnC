package presentation.hotel.view.hotelPromotion.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.hotel.model.HotelPromotionModel;
import vo.promotion.HotelPromotionVo;

public class HotelPromotionMainController implements Initializable{
	@FXML
	private Button modifyBirth;
	
	@FXML
	private Button modifyMulti;
	
	@FXML
	private Button sendBirth;
	
	@FXML
	private Button sendMulti;
	
	@FXML
	private Button addEnter;
	
	@FXML
	private Label birthday;
	
	@FXML
	private Label multiRooms;
	
	@FXML
	private TableView<HotelPromotionModel> promotionTable;
	
	@FXML
	private TableColumn<HotelPromotionModel, String> description;
	
	@FXML
	private TableColumn<HotelPromotionModel,HotelPromotionVo> operation;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		promotionTable.setEditable(false);
		
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  initPromotion();
			  }
		});
		
	}
	
	private void initPromotion(){
		
	}

}
