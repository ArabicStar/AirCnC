package aircnc.test.po;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import po.OrderPo;

public class OrderPoTest {
	
	/**
	 * 这些需要用到Dao
	 * 
	 */
	
	@Test
	public void OrderPoTest1() {
		OrderPo orderPo = new OrderPo(1, 1, 1, 0, "2016.11.6", "2016.11.7", "", 200, 222, 2, 0);
		// TODO: 撤销订单
		orderPo.setStatus(3);
		assertEquals(orderPo.getStatus(), 3);
	}
	
	@Test
	public void OrderPoTest2() {
		OrderPo orderPo = new OrderPo(1, 1, 1, 0, "2016.11.6", "2016.11.7", "", 200, 222, 2, 0);
		
		// TODO: 使订单异常
		orderPo.setStatus(2);
		assertEquals(orderPo.getStatus(), 2);
	}
	
	@Test
	public void OrderPoTest3() {
		OrderPo orderPo = new OrderPo(1, 1, 1, 0, "2016.11.6", "2016.11.7", "", 200, 222, 2, 0);
		
		// TODO: 使订单执行
		orderPo.setStatus(1);
		assertEquals(orderPo.getStatus(), 1);
	}

}
