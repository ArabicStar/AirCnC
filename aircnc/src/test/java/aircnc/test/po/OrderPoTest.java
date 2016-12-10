package aircnc.test.po;

import java.time.LocalDateTime;
import java.util.List;

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
				.setHotelName("乐天玛特").setLastTime(entryTime).setOrderId("201636").setPeopleNumber(3).setPrice(200)
				.setIsReviewed(true).setRoomNumber(1).setRoomType("标准间").setStayDays(2).setUserId(20808121)
				.setStatus(OrderStatus.EXECUTED).getOrderInfo().setUserName("南京大学渣");
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
		orderPo.setLastTime(HotelDate.delayTime(orderPo.getLastTime(), 1, 1, 1));
		orderDao.updateOrder(orderPo);

	}

	// @Test
	// public void OrderPoTest3() {
	// List<OrderPo> list = orderDao.getOrders(1000);
	// for(OrderPo orderPo : list) {
	// System.out.println(orderPo.getEntryTime());
	// }
	// }

	@Test
	public void OrderPoTest4() {
		orderDao.deleteOrder("201636");
	}

}
