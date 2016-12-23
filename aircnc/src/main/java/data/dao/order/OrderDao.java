package data.dao.order;

import po.order.OrderPo;

public interface OrderDao {

	public OrderPo getOrder(String orderId);

	public boolean updateOrder(OrderPo orderPo);

	public boolean addOrder(OrderPo orderPo);

	public boolean deleteOrder(String orderId);

	public boolean existsOrder(String orderId);

}
