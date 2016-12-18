package service.market;

import java.util.Set;

import utils.info.market.MarketInfo;
import vo.promotion.PromotionVo;

/**
 * Interface for account operation<br>
 * 
 * @author paranoia
 *
 */
public interface MarketInfoService {
	
	/**
	 * get Market basic info
	 * @param id
	 * @return
	 */
	public MarketInfo getMarketInfo(String id);
	/**
	 * @param  id
	 * @return	获取促销策略
	 */
	public Set<PromotionVo> getMarketPromotion();
}
