package presentation.hotel.accessor;

import java.util.Set;

import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public interface SearchOrderAccessor {
	public Set<OrderStatus> getStatus();
	
	public void setSearchTarget(Set<OrderStatus> target);
	
	public void setOrderVo(OrderVo vo);
	
	public OrderVo getOrderVo();
}
