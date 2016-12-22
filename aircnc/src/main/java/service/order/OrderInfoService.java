package service.order;

import utils.info.order.OrderInfo;

public interface OrderInfoService {
	public OrderInfo findOrder(String orderId);
}
