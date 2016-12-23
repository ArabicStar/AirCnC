package service.order;

import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import vo.order.OrderVo;

public interface OrderOperationService {
	public OrderInfo makeOrder(OrderVo info);

	public MemberInfo cancelOrder(OrderVo info);

	public MemberInfo executeOrder(OrderVo info);

	public MemberInfo delayOrder(OrderVo info);

	public MemberInfo overdueOrder(OrderVo info);

	public MemberInfo appealOrder(OrderVo info);
}
