package data.dao.query;

import java.util.List;

import po.order.OrderPo;
import utils.info.order.OrderStatus;

public interface OrderQueryDao {
	public List<OrderPo> searchByMember(String memberId);

	public List<OrderPo> searchByHotel(int hotelId);

	public List<OrderPo> searchByStatus(OrderStatus status);
}
