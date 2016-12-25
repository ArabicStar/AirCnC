package service.market;

import utils.info.market.MarketInfo;

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

}
