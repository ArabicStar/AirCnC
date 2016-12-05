package presentation.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import presentation.member.manager.MyOrderManager;
import presentation.member.manager.UserInfoManager;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import utils.info.order.OrderStatus;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

/**
 * 这是一个迟早要删掉的类，我就不用英文勒，这里就模拟的是member的逻辑层
 * @author paranoia
 *
 */
public class MemberTest {
	
	public static UserInfoManager getUserData(){
		UserInfoManager memberInfoManager = new MemberInfoManagerImpl();
		MemberVoBuilder builder =  new MemberVoBuilder("PERSONAL").setId("00002222").setName("hhh").setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").setFixedPhone("0511-12344444")
						.setMobilePhone("13822222222").getContactInfo()).setCredit(0);
		MemberVo vo = builder.getMemberInfo();
		memberInfoManager.setUser(vo);
		return memberInfoManager;
	}
	
	public static MyOrderManager getMyOrderData(){
		List<OrderVo> list = new ArrayList<OrderVo>();
		MyOrderManager manager = new MyOrderManagerImpl();
		OrderVo vo1 =  new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false)
        		.setHotelId(1000).setHotelName("乐天玛特").setLastTime(LocalDateTime.now())
        		.setOrderId("201636").setPeopleNumber(3).setPrice(200)
        		.setIsReviewed(true).setRoomNumber(1).setRoomType("标准间")
        		.setStayDays(2).setUserId(20808121).setStatus(OrderStatus.EXECUTED).setUserName("南京大学渣")
        		.getOrderInfo();
		OrderVo vo2 =  new OrderVoBuilder().setEntryTime(LocalDateTime.now()).setHasChildren(false)
        		.setHotelId(1000).setHotelName("速八酒店").setLastTime(LocalDateTime.now())
        		.setOrderId("201636").setPeopleNumber(3).setPrice(200)
        		.setIsReviewed(true).setRoomNumber(1).setRoomType("标准间")
        		.setStayDays(2).setUserId(20808121).setStatus(OrderStatus.ABNORMAL).setUserName("南京大学渣")
        		.getOrderInfo();
		list.add(vo1); list.add(vo2);
		manager.setOrderList(list);
		return manager;
	}
	
}
