package service.order;

import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;

public interface OrderOperationService {
	public OrderInfo makeOrder(OrderInfo info);

	public MemberInfo cancelOrder(OrderInfo info);

	public MemberInfo executeOrder(OrderInfo info);

	public MemberInfo delayOrder(OrderInfo info);

	public MemberInfo overdueOrder(OrderInfo info);

	public OrderInfo appealOrder(OrderInfo info);

	public OrderInfo commentOrder(OrderInfo info);

	public MemberInfo approveAppeal(OrderInfo info);
}
