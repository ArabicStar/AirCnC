package data.dao.order;

import po.order.OrderPo;

public interface OrderDao {

	/**
	 * @param orderId
	 * @return 获取订单信息
	 */
	public OrderPo getOrder(String orderId);

	/**
	 * @param orderPo
	 * @return 更新订单信息
	 */
	public boolean updateOrder(OrderPo orderPo);

	/**
	 * @param orderPo
	 * @return 添加订单
	 */
	public boolean addOrder(OrderPo orderPo);

	/**
	 * @param orderId
	 * @return 删除订单
	 */
	public boolean deleteOrder(String orderId);

	public boolean existsOrder(String orderId);

}
