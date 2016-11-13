package service.impl.promotion;

import po.UserPo;
import po.order.OrderPo;
import service.promotion.PromotionService;
import vo.order.OrderVo;

public class PromotionServiceImpl implements PromotionService {

	@Override
	public OrderVo apply(OrderVo orderVo) {
		
		return null;
	}

	@Override
	public OrderVo giveDiscounts(OrderVo orderVo, double discountRate) {
		// TODO Auto-generated method stub
		if(discountRate >= 1) {
			return null;
		}
		return new OrderVo(new OrderPo(), new UserPo());
	}

	@Override
	public OrderVo returnMoney(OrderVo orderVo, double minSpentMoney, double returnedMoney) {
		// TODO Auto-generated method stub
		if(minSpentMoney <= returnedMoney) {
			return null;
		}
		return new OrderVo(new OrderPo(), new UserPo());
	}

}
