package aircnc.test.po;

import static org.junit.Assert.assertEquals;

import javax.persistence.criteria.Order;

import org.junit.Test;

import po.order.OrderPo;
import utils.info.order.OrderStatus;

public class OrderPoTest {
	
	/**
	 * FIXME：重写测试代码！
	 * 这些需要用到Dao
	 * 
	 */
	
	@Test
	public void OrderPoTest1() {
//		OrderPo orderPo = new OrderPo("1", 1, 1, OrderStatus.UNEXECUTED, 
//				null, null, "", 200, 222, 2, 0);
		// TODO:为orderPo赋值
		OrderPo orderPo = new OrderPo();
		// TODO: 撤销订单
		orderPo.setStatus(OrderStatus.REPEALED);
		assertEquals(orderPo.getStatus(), OrderStatus.REPEALED);
	}
	
	@Test
	public void OrderPoTest2() {
//		OrderPo orderPo = new OrderPo("1", 1, 1, OrderStatus.UNEXECUTED, 
//				null, null, "", 200, 222, 2, 0);
		// TODO:为orderPo赋值
		OrderPo orderPo = new OrderPo();
		
		// TODO: 使订单异常
		orderPo.setStatus(OrderStatus.ABNORMAL);
		assertEquals(orderPo.getStatus(), OrderStatus.ABNORMAL);
	}
	
	@Test
	public void OrderPoTest3() {
//		OrderPo orderPo = new OrderPo("1", 1, 1, OrderStatus.UNEXECUTED, 
//				null, null, "", 200, 222, 2, 0);
		// TODO:为orderPo赋值
		OrderPo orderPo = new OrderPo();
		// TODO: 使订单执行
		orderPo.setStatus(OrderStatus.EXECUTED);
		assertEquals(orderPo.getStatus(), OrderStatus.EXECUTED);
	}

}
