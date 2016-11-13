package data.datahelper;

import java.util.Map;

import po.order.OrderPo;

public interface OrderDataHelper {
	
	/**
	 * @return	从数据文件中读取订单数据
	 */
	public Map<Integer, OrderPo> getOrderData();
	
	/**
	 * 向数据文件中写入订单数据
	 * @param list	
	 */
	public void updateOrderData(Map<Integer,OrderPo> map);

}
