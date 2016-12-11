package presentation.member.accessor;

import java.util.Set;

import utils.info.order.OrderStatus;

public interface SearchOrderInfoAccessor {

	public Set<OrderStatus> getStatus();
}
