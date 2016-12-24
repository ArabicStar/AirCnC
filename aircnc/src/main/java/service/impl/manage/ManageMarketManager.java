package service.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.Random;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.manage.ManageMarketService;
import utils.info.market.MarketInfo;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public class ManageMarketManager implements ManageMarketService {

	private static ManageMarketService instance;

	public static ManageMarketService launch(MarketDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new ManageMarketManager(dao);
		return instance;
	}

	public static ManageMarketService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private static final Random NEW_ID_GENERATOR = new Random(System.currentTimeMillis());
	private static final int ID_BOUND = 100000000;
	private MarketDao dao;

	private ManageMarketManager(MarketDao dao) {
		this.dao = dao;
	}

	@Override
	public MarketVo AddMarketInfo(MarketVoBuilder newMarketInfo, int passwordHash) {
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
	public boolean ModifyMarketInfo(MarketInfo marketInfo) {
		MarketPo po = dao.findMarket(marketInfo.getId());
		
		
		if (!marketInfo.getId().equals(po.getId()))
			throw new IllegalArgumentException("Incorresponding Market Info");

		return dao
				.updateMarket(new MarketPoBuilder(marketInfo).setPasswordHash(po.getPasswordHash()).getMarketInfo());
	}

	@Override
	public MarketInfo getMarketInfo(String id) {
		if (dao == null)
			throw unsupportedOpEx("get market info");
		if (!MarketInfo.checkID(id))
			throw illegalArgEx("Market id");

		final MarketPo po = dao.findMarket(id);

		return po == null ? null : new MarketVoBuilder(dao.findMarket(id)).getMarketInfo();
	}

	@Override
	public boolean deleteMarketInfo(String id) {
		if (!MarketInfo.checkID(id))
			return false;
		
		return dao.deleteMarket(id);
	}

}
