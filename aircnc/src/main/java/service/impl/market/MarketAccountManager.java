package service.impl.market;

import java.util.Random;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.market.MarketAccountService;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public final class MarketAccountManager implements MarketAccountService {
	private static final Random NEW_ID_GENERATOR = new Random(System.currentTimeMillis());
	private static final int ID_BOUND = 100000000;
	private MarketDao dao;

	private boolean isLogined = false;
	private MarketVo loginedMember = null;

	@Override
	public MarketVo register(MarketVoBuilder newMarketInfo, int passwordHash) {
		String newID = generateNewID();
		MarketVo newMarketVo = newMarketInfo.setID(newID).getMarketInfo();
		MarketPo newMarketPo = new MarketPoBuilder(newMarketVo).setPasswordHash(passwordHash).getMarketInfo();
		boolean result = dao.addMarket(newMarketPo);
		if (result)
			return newMarketVo;

		return MarketVoBuilder.getInvalidInfo();
	}

	// generate a never-used id
	private String generateNewID() {
		int i = NEW_ID_GENERATOR.nextInt(ID_BOUND);
		String newID;
		while (dao.existsMarket(newID = formatID(i)))
			i = NEW_ID_GENERATOR.nextInt(ID_BOUND);

		return newID;
	}

	// format an id
	private static String formatID(int i) {
		return String.format("%08d", i);
	}

	@Override
	public MarketVo login(String id, int passwordHash) {
		if (!existsMarket(id))
			return null;

		MarketPo memberAccount = dao.findMarket(id);
		if (!checkPassword(memberAccount, passwordHash))
			return MarketVoBuilder.getInvalidInfo();

		this.isLogined = true;
		this.loginedMember = new MarketVoBuilder(memberAccount).getMarketInfo();
		return loginedMember;
	}

	@Override
	public boolean logout() {
		this.isLogined = false;
		this.loginedMember = null;
		return true;
	}

	@Override
	public boolean isLogined() {
		return isLogined;
	}

	@Override
	public MarketVo getLoginedMarket() {
		return loginedMember;
	}

	@Override
	public boolean existsMarket(String id) {
		return dao.existsMarket(id);
	}

	private static boolean checkPassword(MarketPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}
}