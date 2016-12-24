package presentation.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.manage.manager.impl.ManageHotelCommentManagerImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import presentation.manage.manager.impl.MemberManageInfoImpl;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;
import vo.order.comment.CommentVo;
import vo.order.comment.CommentVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * 
 * @author paranoia
 *
 */
public class ManageTest {

	public ManageTest() {
		
	}
	
	MemberVo memberVo;
	HotelVo hotelVo;

	public void getUserData() {
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh")
				.setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo())
				.setCredit(0);
		memberVo = builder.getMemberInfo();
		MemberManageInfoImpl.getInstance().setUser(memberVo);
	}

	public void getHotelData() {
		List<HotelVo> list = new ArrayList<HotelVo>();
		HotelVoBuilder builder = new HotelVoBuilder().setID(2).setGrade(4.8)
				.setEquipment("wifi;停车场;24小时热水;叫醒服务").setStar(7)
				.setName("阿拉伯之星").setScope("栖霞区").setLocation("仙林大道")
				.setIntro("我们表面上看起来只是个学校，其实……嘿嘿嘿");
		
		Set<Room> rooms = new HashSet<Room>();
		RoomBuilder roombuilder = new RoomBuilder("SIGNLE").setRoomNum(50).setPrice(245).setId(111).setPeopleNum(1);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("DOUBLE").setRoomNum(20).setPrice(400).setId(111).setPeopleNum(1);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("TRIPLE").setRoomNum(10).setPrice(588).setId(111).setPeopleNum(1);
		rooms.add(roombuilder.getRoomInfo());
		
		roombuilder = new RoomBuilder("4人开黑房").setRoomNum(10).setPeopleNum(4).setPrice(698).setId(111);
		rooms.add(roombuilder.getRoomInfo());
		
		builder.setRooms(rooms);
		
		hotelVo = builder.getHotelInfo();
		
		list.add(hotelVo);
		HotelManageInfoManagerImpl.getInstance().setHotel(hotelVo);
	}
	
	public void getMarketData() {
		MarketVoBuilder builder = new MarketVoBuilder().setID("66666666").setName("孟阳");
		MarketVo marketVo = builder.getMarketInfo();
		MarketManageInfoManagerImpl.getInstance().setMarket(marketVo);
	}
	
	public void getCommentData() {
		List<CommentVo> comments = new ArrayList<CommentVo>();
		CommentVoBuilder builder = new CommentVoBuilder().setContent("环境极差，找了几个同学一起来晚，半夜还撞鬼了。")
				.setCommentTime(LocalDateTime.now()).setGrade(3).setHotel(hotelVo);
		OrderVo vo1 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.EXECUTED).setComment(builder.getCommentInfo()).getOrderInfo();
		OrderVo vo2 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.ABNORMAL).setComment(builder.getCommentInfo()).getOrderInfo();
		OrderVo vo3 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.UNEXECUTED).setComment(builder.getCommentInfo()).getOrderInfo();
		comments.add(vo1.getComments());
		comments.add(vo1.getComments());
		comments.add(vo1.getComments());
		comments.add(vo1.getComments());
		ManageHotelCommentManagerImpl.getInstance().setComment(comments);
	}
}
