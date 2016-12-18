package service.market;

import java.util.List;

import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public interface MarketService {

	/**
	 * @return	获取异常订单
	 */
	public List<OrderVo> getAbnormalOrder();
	
	/**
	 * @param  id
	 * @return	获取促销策略
	 */
	public List<PromotionVo> makeMarketPromotion(String id);
	
	public boolean creditCharge(int money, String id);

}
