package presentation.hotel;

import java.util.HashSet;
import java.util.Set;

import presentation.hotel.manager.InfoManager;
import presentation.hotel.manager.impl.InfoManagerImpl;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public class HotelTest {
	InfoManager hotelInfoManager;
	
	HotelVo hotel;
	
	public HotelTest(){
		HotelVoBuilder builder = new HotelVoBuilder().setID(2).setGrade(4.8)
				.setEquipment("wifi;停车场;24小时热水;叫醒服务").setStar(7)
				.setName("阿拉伯之星").setScope("栖霞区").setLocation("仙林大道163号")
				.setIntro("我们表面上看起来只是个学校，其实……嘿嘿嘿");
		
		Set<Room> rooms = new HashSet<Room>();
		RoomBuilder roombuilder = new RoomBuilder("SIGNLE").setRoomNum(50).setPrice(245);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("DOUBLE").setRoomNum(20).setPrice(400);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("TRIPLE").setRoomNum(10).setPrice(588);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("4人开黑房").setRoomNum(10).setPeopleNum(4).setPrice(698);
		rooms.add(roombuilder.getRoomInfo());
		
		builder.setRooms(rooms);
		
		hotel = builder.getHotelInfo();
		
		
	}
	
	public InfoManager getHotelData() {
		hotelInfoManager = new InfoManagerImpl();
		hotelInfoManager.setHotel(hotel);
		return hotelInfoManager;
	}
}
