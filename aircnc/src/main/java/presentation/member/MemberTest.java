package presentation.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import presentation.member.manager.CreditChangeManager;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
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

	UserInfoManager memberInfoManager;
	MyOrderManager myOrderManager;
	CreditChangeManager creditManager;
	SearchHotelManager searchManager;

	MemberVo memberVo;

	public MemberTest() {
		MemberVoBuilder builder = new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh")
				.setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo())
				.setCredit(0);
		memberVo = builder.getMemberInfo();
	}

	public UserInfoManager getUserData() {
		memberInfoManager = new MemberInfoManagerImpl();
		memberInfoManager.setUser(memberVo);
		return memberInfoManager;
	}

	public MyOrderManager getMyOrderData() {
		List<OrderVo> list = new ArrayList<OrderVo>();
		myOrderManager = new MyOrderManagerImpl();
		OrderVo vo1 = new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotelId(1000)
				.setHotelName("乐天玛特").setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setReviewed(true).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.EXECUTED).setUserName("南京大学渣").getOrderInfo();
		OrderVo vo2 = new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotelId(1000)
				.setHotelName("速八酒店").setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setReviewed(true).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.ABNORMAL).setUserName("南京大学渣").getOrderInfo();
		OrderVo vo3 = new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false).setHotelId(1000)
				.setHotelName("速八酒店").setLastTime(LocalDateTime.now()).setOrderId("201636").setPeopleNumber(3)
				.setOriginalPrice(200).setReviewed(true).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.UNEXECUTED).setUserName("南京大学渣").getOrderInfo();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		myOrderManager.setOrderList(list);
		return myOrderManager;
	}

	public CreditChangeManager getCreditData() {
		List<CreditChangeVo> list = new ArrayList<CreditChangeVo>();
		creditManager = new CreditChangeManagerImpl();
		CreditChangeVo vo1 = new CreditChangeVoBuilder(memberVo, ActionType.CHARGE).setCreditChange(23300).setMoney(233)
				.getCreditChangeInfo();
		CreditChangeVo vo2 = new CreditChangeVoBuilder(memberVo, ActionType.ORDER_APPEAL).setCreditChange(23300)
				.setOrderId("6666").getCreditChangeInfo();
		CreditChangeVo vo3 = new CreditChangeVoBuilder(memberVo, ActionType.ORDER_OVERDUE).setCreditChange(23300)
				.setOrderId("66666").getCreditChangeInfo();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		creditManager.setCreditChanges(list);
		return creditManager;
	}

	public SearchHotelManager getSearchedData() {
		List<HotelVo> list = new ArrayList<HotelVo>();
		searchManager = new SearchHotelManagerImpl();
		HotelVo vo1 = new HotelVoBuilder().setID(00002222).setName("速八酒店").setGrade(4.5).setScope("市中心")
				.setLocation("新街口").setStar(4).getHotelInfo();
		HotelVo vo2 = new HotelVoBuilder().setID(00002222).setName("如家酒店").setGrade(4.5).setScope("市中心")
				.setLocation("新街口").setStar(4).getHotelInfo();
		HotelVo vo3 = new HotelVoBuilder().setID(00002222).setName("布丁酒店").setGrade(4.5).setScope("市中心")
				.setLocation("新街口").setStar(4).getHotelInfo();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		searchManager.setHotel(list);
		return searchManager;
	}
	
	public void ChangeOrder(){
		
	}
}
