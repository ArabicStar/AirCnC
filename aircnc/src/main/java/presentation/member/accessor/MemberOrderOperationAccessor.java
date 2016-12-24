package presentation.member.accessor;

import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public interface MemberOrderOperationAccessor {
	
	public OrderVoBuilder getOrder();
	
	public OrderVo getCanceledOrder();
	
	public void setComment(OrderVo vo, double rate, String content);
	
	public void setAppeal(OrderVo vo, String content);
	
	public void setCancel(OrderVo vo);
}
