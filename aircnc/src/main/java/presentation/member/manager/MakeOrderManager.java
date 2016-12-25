package presentation.member.manager;

import vo.order.OrderVo;

public interface MakeOrderManager {
	
	public void setOrderVo(OrderVo vo);
	
	public OrderVo getOrderVo();
}
