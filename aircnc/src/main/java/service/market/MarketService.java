package service.market;

import java.util.List;

import vo.order.OrderVo;
import vo.promotion.WebsitePromotionVo;

public interface MarketService {

	/**
	 * @return	获取异常订单
	 */
	public List<OrderVo> getAbnormalOrder();
	
	/**
	 * @param  id
	 * @return	获取促销策略
	 */
	public boolean makeMarketPromotion(WebsitePromotionVo promotion);
	
	public boolean deletePromotion(WebsitePromotionVo vo);
	
	public boolean updatePromotion(WebsitePromotionVo vo);
	
	public boolean creditCharge(int money, String id);

}
