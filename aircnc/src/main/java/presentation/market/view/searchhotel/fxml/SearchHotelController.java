package presentation.market.view.searchhotel.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.market.MarketCenterController;
import presentation.market.utils.TextAreaDialog;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class SearchHotelController implements Initializable {
	@SuppressWarnings("unused")
	private MarketCenterController controller;
	
	@FXML
	private TextField hotelName;
	
	@FXML
	private Label supreme;
	
	
	@FXML
	private void handleMakeOrder() {
		HotelVo hotelVo = new HotelVoBuilder().setID(123).setGrade(5.0).setEquipment("Wifi")
				.setIntro("1e214").setLocation("341413").setName("南京大酒店").setScope("南京")
				.setStar(5).getHotelInfo();
		
		TextAreaDialog textAreaDialog = new TextAreaDialog("生成订单", hotelVo);

		textAreaDialog.showDialog();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCenterController(MarketCenterController controller) {
		this.controller = controller;
	}
}
