package data.dao.order;

import po.order.OrderPo;

public interface OrderDao {

	/**
	 * Get order by id.
	 * 
	 * @param orderId
	 * @return order
	 */
	public OrderPo getOrder(String orderId);

	/**
	 * Update order.
	 * 
	 * @param orderPo
	 * @return result of operation
	 */
	public boolean updateOrder(OrderPo orderPo);

	/**
	 * Add order.
	 * 
	 * @param orderPo
	 * @return result of operation
	 */
	public boolean addOrder(OrderPo orderPo);

	/**
	 * Delete order by id.
	 * 
	 * @param orderId
	 * @return result of operation
	 */
	public boolean deleteOrder(String orderId);

	/**
	 * Check given order id exists or not
	 * 
	 * @param orderId
	 * @return existence
	 */
	public boolean existsOrder(String orderId);

}
