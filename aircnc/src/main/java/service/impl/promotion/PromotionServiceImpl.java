package service.impl.promotion;

import data.dao.member.MemberDao;
import po.order.OrderPo;
import service.promotion.PromotionService;
import vo.order.OrderVo;

public class PromotionServiceImpl implements PromotionService {
	/**
	 * FIXME:此处没有初始化memberDao，目标是在使用此处代码的时候报空指针
	 */
	MemberDao memberDao;
	
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
		
		/**
		 * TODO:返回值应该修改
		 */
		return new OrderVo();
	}

	@Override
	public OrderVo returnMoney(OrderVo orderVo, double minSpentMoney, double returnedMoney) {
		// TODO Auto-generated method stub
		if(minSpentMoney <= returnedMoney) {
			return null;
		}
		/**
		 * TODO:返回值应该修改
		 */
		return new OrderVo();
	}

}
