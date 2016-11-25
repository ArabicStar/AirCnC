package aircnc.test.utils.info.order;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.info.order.OrderInfoTemplate;

public class OrderInfoTemplateTest {
	@Test
	public void checkOrderIdTest1() {
		String orderId = "2016010200010001";
		assertEquals(true, OrderInfoTemplate.checkOrderId(orderId));
	}
	
	@Test
	public void checkOrderIdTest2() {
		String orderId = "20160101000100001";
		assertEquals(false, OrderInfoTemplate.checkOrderId(orderId));
	}
	
	@Test
	public void checkOrderIdTest3() {
		String orderId = "20001";
		assertEquals(false, OrderInfoTemplate.checkOrderId(orderId));
	}
	
	@Test
	public void checkOrderIdTest4() {
		String orderId = "201601020001000x";
		assertEquals(false, OrderInfoTemplate.checkOrderId(orderId));
	}
}
