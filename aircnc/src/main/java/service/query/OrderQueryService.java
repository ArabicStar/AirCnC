package service.query;

import java.util.List;

import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public interface OrderQueryService {
	public List<OrderVo> getMemberOrders(String memberId);

	public List<OrderVo> getHotelOrders(String memberId);

	public List<OrderVo> getOrdersOfStatus(OrderStatus status);
}
