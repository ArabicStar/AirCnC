package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.Set;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import service.market.MarketInfoService;
import service.promotion.WebsitePromotionInfoService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;
import vo.promotion.PromotionVo;
/**
 * Implementation of MarketInfoService and MarketQueryService.<br>
 * Singleton.<br>
 * 
 * @see service.market.MarketInfoService
 * @see service.query.MarketQueryService
 * @author paranoia
 *
 */
public final class MarketInfoManager implements MarketInfoService{
	/****** singleton ******/
	private static MarketInfoManager instance;

	/**
	 * Singleton instance initializer.<br>
	 * Parameters are daos or services depended on in some methods. They are
	 * alternative, <b>null</b> is allowed. In that case, when call methods
	 * which need the null dependency, {@code IllegalStateException} will be
	 * thrown to notify a unsupported operation is attempted.<br>
	 * Specific dependencies of each methods will be explained in methods'
	 * comments.<br>
	 * 
	 * @param marketDao
	 *            dao market
	 * @param websitePromotionInfoService
	 *            promotion service
	 * @return initialized instance
	 * 
	 * @throws IllegalStateException
	 *             singleton has existed already <br>
	 */
	public static MarketInfoManager launch(final MarketDao marketDao
			, final WebsitePromotionInfoService websitePromotionInfoService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketInfoManager(marketDao, websitePromotionInfoService);
	}

	/**
	 * Get singleton instance.<br>
	 * 
	 * @return singleton instance
	 * @throws IllegalStateException
	 *             if singleton doesn't exist yet <br>
	 */
	public static MarketInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private MarketDao marketDao;
	private WebsitePromotionInfoService websitePromotionInfoService;

	private MarketInfoManager(MarketDao marketDao, WebsitePromotionInfoService websitePromotionInfoService) {
		this.marketDao = marketDao;
		this.websitePromotionInfoService = websitePromotionInfoService;
	}

	/*
	 ******************************************************
	 * As follow are MarketInfoService Implementions
	 ******************************************************
	 */
	@Override
	public MarketInfo getMarketInfo(final String id) {
		if (marketDao == null)
			throw unsupportedOpEx("get market info");

		if (!MarketInfo.checkID(id))
			throw illegalArgEx("Market id");

		final MarketPo po = marketDao.findMarket(id);

		return po == null ? null : new MarketVoBuilder(marketDao.findMarket(id)).getMarketInfo();
	}
	
	private Set<PromotionVo> bufferedPromotionList;
	
	@Override
	public Set<PromotionVo> getMarketPromotion() {
		if(websitePromotionInfoService == null)
			throw unsupportedOpEx("get market promotion info");
		
		Set<PromotionVo> res = websitePromotionInfoService.getUserAvailableWebsitePromotions();

		// given id not exists, return
		if (res == null)
			return null;
		
		bufferedPromotionList = res;

		return bufferedPromotionList;
	}
	
}