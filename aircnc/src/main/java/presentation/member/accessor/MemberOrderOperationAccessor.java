package presentation.member.accessor;

import vo.order.OrderVo;

public interface MemberOrderOperationAccessor {
	
	public OrderVo getOrder();
	
	public OrderVo getCanceledOrder();
	
	public void setComment(OrderVo vo, double rate, String content);
	
	public void setAppeal(OrderVo vo, String content);
	
	public void setCancel(OrderVo vo);
	
	public void setOrder(OrderVo vo);
}
