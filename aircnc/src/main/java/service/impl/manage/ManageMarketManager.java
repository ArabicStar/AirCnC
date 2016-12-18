package service.impl.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Random;

import data.dao.market.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.manage.ManageMarketService;
import utils.info.market.MarketInfo;
import utils.info.member.MemberInfo;
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
	public boolean ModifyMarketInfo(MarketInfo hotelInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMarketInfo(MarketInfo hotelInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMarketInfo(String id) {
		if (!MarketInfo.checkID(id))
			return false;
		
		return dao.deleteMarket(id);
	}

}
