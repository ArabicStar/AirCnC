package presentation.manage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.accessor.impl.MemberManageInfoAccessorImpl;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.manager.impl.ManageHotelCommentManagerImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * 
 * @author paranoia
 *
 */
public class ManageTest {

	public ManageTest() {
		
	}

	public static void getUserData() {
		if(!MemberManageInfoImpl.isLaunched())
			MemberManageInfoImpl.launch();
		if(!MemberManageInfoAccessorImpl.isLaunched())
			MemberManageInfoAccessorImpl.launch();
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh")
				.setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo())
				.setCredit(0);
		MemberVo memberVo = builder.getMemberInfo();
		MemberManageInfoImpl.getInstance().setUser(memberVo);
	}

	public static void getHotelData() {
		if(!HotelManageInfoManagerImpl.isLaunched())
			HotelManageInfoManagerImpl.launch();
		if(!HotelManageInfoAccessorImpl.isLaunched())
			HotelManageInfoAccessorImpl.launch();
		if(!ManageHotelCommentManagerImpl.isLaunched())
			ManageHotelCommentManagerImpl.launch();
		List<HotelVo> list = new ArrayList<HotelVo>();
		HotelVoBuilder builder = new HotelVoBuilder().setID(2).setGrade(4.8)
				.setEquipment("wifi;停车场;24小时热水;叫醒服务").setStar(7)
				.setName("阿拉伯之星").setScope("栖霞区").setLocation("仙林大道")
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
		
		HotelVo hotel = builder.getHotelInfo();
		
		list.add(hotel);
		HotelManageInfoManagerImpl.getInstance().setHotel(hotel);
	}
	
	public static void getMarketData() {
		if(!MarketManageInfoManagerImpl.isLaunched())
			MarketManageInfoManagerImpl.launch();
		if(!MarketManageInfoAccessorImpl.isLaunched())
			MarketManageInfoAccessorImpl.launch();
		MarketVoBuilder builder = new MarketVoBuilder().setID("66666666").setName("孟阳");
		MarketVo marketVo = builder.getMarketInfo();
		MarketManageInfoManagerImpl.getInstance().setMarket(marketVo);
	}
}
