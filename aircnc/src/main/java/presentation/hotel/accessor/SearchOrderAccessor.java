package presentation.hotel.accessor;

import java.util.Set;

import utils.info.order.OrderStatus;

public interface SearchOrderAccessor {
	public Set<OrderStatus> getStatus();
	
	public void setSearchTarget(Set<OrderStatus> target);
}
