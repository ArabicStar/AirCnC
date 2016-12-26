package presentation.market.accessor;

import vo.order.OrderVo;

public interface AbnormalOrderAccessor {
	public void setOrderVo(OrderVo vo);
	
	public OrderVo getOrderVo();

}
