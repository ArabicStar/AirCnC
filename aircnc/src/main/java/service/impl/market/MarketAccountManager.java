package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import service.market.MarketAccountService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

public final class MarketAccountManager implements MarketAccountService {
	private static MarketAccountService instance;

	public static MarketAccountService launch(MarketDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new MarketAccountManager(dao);
		return instance;
	}

	public static MarketAccountService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private MarketDao dao;

	private boolean isLogined = false;
	/**
	 * Logined market info.<br>
	 * Actually, it should be a MarketPo instance, so casts it when neccessary.
	 * <br>
	 */
	private MarketPo currentAccount = null;
	
	private MarketAccountManager(MarketDao dao) {
		this.dao = dao;
	}

	@Override
	public MarketInfo login(String id, int passwordHash) {
		if (!MarketInfo.checkID(id))
			return null;

		MarketPo marketAccount;
		marketAccount = dao.findMarket(id);

		if (marketAccount == null)// not exist
			return null;

		// password verify
		if (!checkPassword(marketAccount, passwordHash))
			return MarketVoBuilder.getInvalidInfo();

		// set login status
		this.isLogined = true;
		this.currentAccount = marketAccount;
		return currentAccount;
	}

	@Override
	public boolean logout() {
		this.isLogined = false;
		this.currentAccount = null;
		return true;
	}

	@Override
	public boolean isLoggedin() {
		return isLogined;
	}
	
	@Override
	public MarketPo getCurrentAccount() {
		return currentAccount;
	}

	@Override
	public boolean existsMarket(String id) {
		if (!MarketInfo.checkID(id))
			return false;

		return dao.existsMarket(id);
	}

	private static boolean checkPassword(MarketPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}

	@Override
	public MarketInfo refreshCurrentAccount() {
		return currentAccount = dao.findMarket(currentAccount.getId());
	}
}