package service.promotion;

import vo.order.OrderVo;

public interface PromotionService {
	public OrderVo apply(OrderVo orderVo);
	
	public OrderVo giveDiscounts(OrderVo orderVo, double discountRate);
	
	public OrderVo returnMoney(OrderVo orderVo, double minSpentMoney, double returnedMoney);
}
