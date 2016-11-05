package service.impl;

import data.dao.MarketDao;
import data.dao.impl.MarketDaoImpl;
import po.MarketPo;
import service.MarketService;
import vo.MarketVo;

public class MarketServiceImpl implements MarketService{

	private MarketDao marketDao;

	public MarketServiceImpl(){
		marketDao = MarketDaoImpl.getInstance();
	}

	public MarketVo getMarket(String marketId) {
		MarketPo marketPo = marketDao.getMarket(marketId);
		return new MarketVo(marketPo);
	}


}
