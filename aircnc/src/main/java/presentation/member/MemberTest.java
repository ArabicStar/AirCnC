package presentation.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.HistoryOrderManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import utils.info.member.credit.ActionType;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.member.credit.CreditChangeVoBuilder;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * 
 * @author paranoia
 *
 */
public class MemberTest {

	MemberVo memberVo;
	HotelVo hotelVo;

	public MemberTest() {
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh")
				.setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo())
				.setCredit(0);
		memberVo = builder.getMemberInfo();
	}

	public void getUserData() {
		MemberInfoManagerImpl.getInstance().setUser(memberVo);
	}

	public void getMyOrderData() {
		List<OrderVo> list = new ArrayList<OrderVo>();
		OrderVo vo1 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.EXECUTED).getOrderInfo();
		OrderVo vo2 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.ABNORMAL).getOrderInfo();
		OrderVo vo3 = (OrderVo) new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotel(hotelVo)
				.setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setMember(memberVo)
				.setStatus(OrderStatus.UNEXECUTED).getOrderInfo();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		MyOrderManagerImpl.getInstance().setOrderList(list);
		HistoryOrderManagerImpl.getInstance().setOrderList(list);
	}

	public void getCreditData() {
		List<CreditChangeVo> list = new ArrayList<CreditChangeVo>();
		CreditChangeVo vo1 = new CreditChangeVoBuilder(memberVo, ActionType.CHARGE).setCreditChange(23300).setMoney(233)
				.getCreditChangeInfo();
		CreditChangeVo vo2 = new CreditChangeVoBuilder(memberVo, ActionType.ORDER_APPEAL).setCreditChange(23300)
				.setOrderId("6666").getCreditChangeInfo();
		CreditChangeVo vo3 = new CreditChangeVoBuilder(memberVo, ActionType.ORDER_OVERDUE).setCreditChange(23300)
				.setOrderId("6666").getCreditChangeInfo();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		CreditChangeManagerImpl.getInstance().setCreditChanges(list);
	}

	public void getSearchedData() {
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
		
		this.hotelVo = hotel;
		
		list.add(hotel);
		SearchHotelManagerImpl.getInstance().setHotel(list);
	}
	
	public void ChangeOrder(){
		
	}
}
