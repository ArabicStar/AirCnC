package aircnc.test.service.order;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;

import static org.junit.Assert.assertEquals;
import service.impl.order.OrderDetailServiceImpl;
import service.impl.order.OrderListingServiceImpl;
import service.order.OrderDetailService;
import service.order.OrderListingService;
import service.order.OrderLogicService;
import vo.order.OrderVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest {
	OrderDetailService detail;
	OrderListingService listing;
	OrderLogicService logic;
	OrderDao orderDao;
	
	
	@Before
	public void setUp() {
		detail = new OrderDetailServiceImpl();
		listing = new OrderListingServiceImpl(1000);
		orderDao = OrderDaoImpl.INSTANCE;
	}

	@Test
	public void testGetOrderUser() {
		assertEquals(20808121, detail.getOrderUser("2016121010001234"));
	}
	
	@Test
	public void testGetOrderOridinalPrice() {
		assertEquals(200, detail.getOrderOriginalPrice("2016121010001234"), 0.1);
	}
	
	@Test
	public void testGetAllOrders() {
		assertEquals(listing.getAllOrders().size(), 1);
		assertEquals(((OrderVo)listing.getAllOrders().get(0)).getOrderId(), "2016121010001234");
	}
	
	@Test
	public void testGetExecutedOrders() {
		assertEquals(listing.getExecutedOrders().size(), 1);
		assertEquals(((OrderVo)listing.getAllOrders().get(0)).getOrderId(), "2016121010001234");
	}
	
	
	// TODO:实际上应该在OrderPoTest文件里面，且应该命名为testOrderPo4
	@Test
	public void testZZZZZZZ() {
		orderDao.deleteOrder("2016121010001234");
	}

}
