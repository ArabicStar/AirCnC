package presentation.market.view.searchhotel.fxml;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.market.MarketCenterController;
import presentation.market.utils.dialog.TextAreaDialog;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
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
		Set<Room> rooms = new HashSet<>();
		rooms.add(new RoomBuilder("单人间").setPeopleNum(1).setPrice(100).setRoomNum(10).getRoomInfo());
		rooms.add(new RoomBuilder("双人间").setPeopleNum(2).setPrice(200).setRoomNum(20).getRoomInfo());
		rooms.add(new RoomBuilder("三人间").setPeopleNum(3).setPrice(300).setRoomNum(30).getRoomInfo());
		HotelVo hotelVo = new HotelVoBuilder().setID(123).setGrade(5.0).setEquipment("Wifi")
				.setIntro("1e214").setLocation("341413").setName("南京大酒店").setScope("南京")
				.setStar(5).setRooms(rooms).getHotelInfo();
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
