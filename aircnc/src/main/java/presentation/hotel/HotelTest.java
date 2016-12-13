package presentation.hotel;

import java.util.HashSet;
import java.util.Set;

import presentation.hotel.manager.InfoManager;
import presentation.hotel.manager.impl.InfoManagerImpl;
import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.hotel.RoomVo;
import vo.hotel.RoomVoBuilder;

public class HotelTest {
	InfoManager hotelInfoManager;
	
	HotelVo hotel;
	
	public HotelTest(){
		HotelVoBuilder builder = new HotelVoBuilder().setID(2).setGrade(4.8)
				.setEquipment("wifi;停车场;24小时热水;叫醒服务").setStar(5)
				.setName("阿拉伯之星").setScope("栖霞区").setLocation("仙林大道163号")
				.setIntro("我们表面上看起来只是个学校，其实……嘿嘿嘿");
		
		Set<RoomVo> rooms = new HashSet<RoomVo>();
		RoomVoBuilder roombuilder = new RoomVoBuilder("SIGNLE").setRoomNum(50).setPrice(245);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomVoBuilder("DOUBLE").setRoomNum(20).setPrice(400);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomVoBuilder("TRIPLE").setRoomNum(10).setPrice(588);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomVoBuilder("4人开黑房").setRoomNum(10).setPeopleNum(4).setPrice(698);
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
