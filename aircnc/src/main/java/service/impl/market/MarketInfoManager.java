package service.impl.market;

import data.dao.HotelDao;
import data.dao.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.market.MarketInfoService;
import service.query.QueryService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

public class MarketInfoManager implements MarketInfoService{
	private MarketDao marketDao;

	public MarketInfoManager(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	@Override
	public MarketInfo getMarketInfo(String id) {
		// TODO Auto-generated method stub
		return new MarketVoBuilder(marketDao.findMarket(id)).getMarketInfo();
	}

	@Override
	public boolean updateInfo(MarketInfo modifiedInfo) {
		MarketPo po = marketDao.findMarket(modifiedInfo.getId());
		if (po == null)
			return false;

		return marketDao
				.updateMarket(new MarketPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMarketInfo());
	}

}
