package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;
import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.market.MarketAccountService;
import service.market.MarketInfoService;
import service.query.OrderQueryService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;
import vo.order.OrderVo;
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
	 * @param accountService
	 *            account service
	 * @param orderQueryService
	 *            order query service
	 * @return initialized instance
	 * 
	 * @throws IllegalStateException
	 *             singleton has existed already <br>
	 */
	public static MarketInfoManager launch(final MarketDao marketDao
			, final MarketAccountService accountService, final OrderQueryService orderQueryService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketInfoManager(marketDao, accountService, orderQueryService);
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

	private MarketAccountService accountService;
	private OrderQueryService orderQueryService;

	private MarketInfoManager(MarketDao memberDao, MarketAccountService accountService
			, OrderQueryService orderQueryService) {
		this.marketDao = memberDao;
		this.accountService = accountService;
		this.orderQueryService = orderQueryService;
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

	/* MemberQueryService */

	@Override
	public boolean updateBasicInfo(MarketInfo modifiedInfo) {
		if (accountService == null || marketDao == null)
			throw unsupportedOpEx("update market basic info");

		if (modifiedInfo == null || !modifiedInfo.isValid())
			throw illegalArgEx("null or invalid market info");

		if (!accountService.isLoggedin())
			throw illegalStateException("Not logged in yet");

		MarketPo po = (MarketPo) accountService.getCurrentAccount();

		if (po == null)
			return false;

		int comp = MarketVoBuilder.compareMarketInfo(modifiedInfo, po);

		// different member with currently logged in account: denied
		if (comp == -1)
			throw inconsistentStatusEx();

		// modify advanced info: denied
		if ((comp & 2) != 0)
			throw unsupportedOpEx("update advanced market info");

		// no modification: return
		if ((comp & 1) == 0)
			return true;

		return updateInfo(new MarketPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMarketInfo());
	}

	/* MemberQueryService */

	@Override
	public boolean updateAdvancedInfo(MarketInfo modifiedInfo) {
		if (marketDao == null)
			throw unsupportedOpEx("update market advanced info");

		if (modifiedInfo == null || !modifiedInfo.isValid())
			throw illegalArgEx("null or invalid market info");

		final MarketPo po = marketDao.findMarket(modifiedInfo.getId());

		if (po == null)// not exist
			return false;

		int comp = MarketVoBuilder.compareMarketInfo(modifiedInfo, po);

		// modify basic info: denied
		if ((comp & 1) != 0)
			throw unsupportedOpEx("update basic market info");

		// no modification: return
		if ((comp & 2) == 0)
			return true;

		return updateInfo(new MarketPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMarketInfo());
	}

	@Override
	public boolean updatePassword(int oldPwdHash, int newPwdHash) {
		if (accountService == null || marketDao == null)
			throw unsupportedOpEx("update password");

		if (!accountService.isLoggedin())
			throw illegalStateException("Not logged in yet");

		MarketPo po = (MarketPo) accountService.getCurrentAccount();

		if (po.getPasswordHash() != oldPwdHash)
			return false;

		return updateInfo(new MarketPoBuilder(po).setPasswordHash(newPwdHash).getMarketInfo());
	}

	private boolean updateInfo(MarketPo modifiedInfo) {
		return marketDao.updateMarket(modifiedInfo);
	}

	/* Buffered member order query service */
	private String bufferedId = null;
	private List<OrderVo> bufferedOrderList;
	private boolean dirtyBuffer = false;

	@Override
	public List<OrderVo> getAllExceptionOrders(final String id) {
		if (orderQueryService == null)
			throw unsupportedOpEx("get market orders");

		if (!MarketInfo.checkID(id))
			throw illegalArgEx("Market id");

		/* different id from buffered one */
		if (bufferedId == null || !bufferedId.equals(id)) {
			// get
			List<OrderVo> res = orderQueryService.getMemberOrders(id);

			// given id not exists, return
			if (res == null)
				return null;

			// exists, refresh buffer
			bufferedId = id;
			bufferedOrderList = res;
			dirtyBuffer = false;

			return bufferedOrderList;
		}

		/* same id with buffered one */
		// reverse buffer dirty indicator
		if (dirtyBuffer = !dirtyBuffer)
			return bufferedOrderList;// return buffered list
		else
			// refresh buffer, same id with buffer assure existence of given id,
			// not need to check again
			return bufferedOrderList = orderQueryService.getMemberOrders(id);
	}

	@Override
	public List<PromotionVo> getHotelPromotion(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}