package aircnc.test.po;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import po.order.OrderPo;
import po.order.OrderPoBuilder;
import utils.date.HotelDate;
import utils.info.order.OrderStatus;

public class OrderPoTest {
	OrderDao orderDao;
	OrderPo orderPo;

	@Before
	public void startUp() {
		orderDao = OrderDaoImpl.INSTANCE;
		LocalDateTime entryTime = LocalDateTime.now();
		orderPo = new OrderPoBuilder().setEntryTime(entryTime).setHasChildren(false).setHotelId(1000)
				.setLastTime(entryTime).setOrderId("2016121010001234").setPeopleNumber(3).setOriginalPrice(200)
				.setReviewed(false).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.EXECUTED).getOrderInfo().setUserName("南京大学渣").setHotelName("乐天玛特");
	}

	/**
	 * FIXME：重写测试代码！ 这些需要用到Dao
	 * 
	 */

	@Test
	public void OrderPoTest1() {
		orderDao.addOrder(orderPo);
	}

	@Test
	public void OrderPoTest2() {
		OrderPo newOrder = orderDao.getOrder("2016121010001234");
		newOrder.setLastTime(HotelDate.delayTime(newOrder.getLastTime(), 1, 1, 1));
		orderDao.updateOrder(newOrder);

	}

	@Test
	public void OrderPoTest3() {
		OrderPo newOrder = orderDao.getOrder("2016121010001234");
		newOrder.setReviewed(true);
		newOrder.setLastTime(HotelDate.delayTime(newOrder.getLastTime(), 3, 5, 6));
		orderDao.updateOrder(newOrder);
		// OrderPo orderPo = orderDao.getOrder("2016121010001234");
		// assertEquals("乐天玛特", orderPo.getHotelName());
	}


	
}
