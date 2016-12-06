package service.query;

import java.util.List;

import vo.order.OrderVo;

public interface OrderQueryService {
	public List<OrderVo> searchByMember(String memberId);

	public List<OrderVo> searchByHotel(String hotelId);
}
