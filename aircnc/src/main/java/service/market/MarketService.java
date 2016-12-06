package service.market;

import java.util.List;

import vo.order.OrderVo;

public interface MarketService {

	/**
	 * @return	获取异常订单
	 */
	public List<OrderVo> getAbnormalOrder();
	
	/**
	 * @param  id
	 * @return	获取促销策略
	 */
	//public List<PromotionVo> getMarketPromotion(String id);

}
