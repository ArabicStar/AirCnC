package presentation.member.manager;

import utils.info.order.OrderInfo;

public interface MakeOrderManager {
	
	public void setOrderVo(OrderInfo vo);
	
	public OrderInfo getOrderVo();
}
