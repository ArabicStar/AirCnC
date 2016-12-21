package aircnc.test.po;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import po.order.OrderPo;
import po.order.OrderPoBuilder;
import po.order.comment.CommentPo;
import po.order.comment.CommentPoBuilder;
import utils.date.HotelDate;
import utils.info.order.OrderStatus;

public class OrderPoTest {
	OrderDao orderDao;
	OrderPo orderPo;

	@Before
	public void startUp() {
		orderDao = OrderDaoImpl.INSTANCE;
		LocalDateTime entryTime = LocalDateTime.now();
		LocalDate today = LocalDate.now();
		CommentPo commentPo = new CommentPoBuilder().setCheckInTime(today).setCommentTime(entryTime)
				.setContent("呵呵哈哈嘻嘻").setGrade(4).setHotelID(1000).setMemberID("20121314")
				.setOrderId("2016121010001234").getCommentInfo();
		orderPo = new OrderPoBuilder().setEntryTime(entryTime).setHasChildren(false).setHotelId(1000)
				.setLastTime(entryTime).setOrderId("2016121010001234").setPeopleNumber(3).setOriginalPrice(200)
				.setReviewed(false).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.EXECUTED).setUserName("南京大学渣")
				.setUserId(20121314).setHotelName("乐天玛特").setComments(commentPo).getOrderInfo().setAppeal("申诉一下");
	}

	@Test
	public void OrderPoTest1() {
		orderDao.addOrder(orderPo);
	}

	@Test
	public void OrderPoTest2() {
		OrderPo newOrder = orderDao.getOrder("2016121010001234");
		newOrder.setAppeal("我就是要申诉");
		orderDao.updateOrder(newOrder);
	}
//
	@Test
	public void OrderPoTest3() {
		OrderPo newOrder = orderDao.getOrder("2016121010001234");
//		newOrder.setReviewed(true);
//		newOrder.setLastTime(HotelDate.delayTime(newOrder.getLastTime(), 3, 5, 6));
//		orderDao.updateOrder(newOrder);
		System.out.println(newOrder.getComments().getContent());
		// OrderPo orderPo = orderDao.getOrder("2016121010001234");
		// assertEquals("乐天玛特", orderPo.getHotelName());
	}


	
}
