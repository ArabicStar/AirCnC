package data.dao.impl.market;

import static data.hibernate.Hibernator.execute;
import data.dao.market.MarketDao;
import po.market.MarketPo;

/**
 * the implementation of MarketDao
 * @author paranoia
 *
 */
public class MarketDaoImpl implements MarketDao{

	@Override
	public boolean addMarket(final MarketPo po) {
		if(po==null)
			return false;
		
		/*
		 * check the valiation
		 */
		if(existsMarket(po.getId()))
			return false;
		
		return execute(session -> {
			// save
			session.save(po);

			return true;
		});
	}

	@Override
	public boolean deleteMarket(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMarket(MarketPo po) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MarketPo findMarket(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsMarket(String id) {
		// TODO Auto-generated method stub
		return false;
	}


	/*private ArrayList<MarketPo> marketData;

	private MarketDataHelper marketDataHelper;

	private DataFactory dataFactory;

	private static MarketDaoImpl marketDataServiceImpl;

	public static MarketDaoImpl getInstance(){
		if(marketDataServiceImpl == null){
			marketDataServiceImpl = new MarketDaoImpl();
		}
		return marketDataServiceImpl;
	}

	public MarketDaoImpl(){
		if(marketData == null){
			dataFactory = new DataFactoryImpl();
			marketDataHelper = dataFactory.getMarketDataHelper();
			marketData = marketDataHelper.getMarketData();
		}
	}


	@Override
	public MarketPo getMarket(String marketId) {
		Iterator<MarketPo> iterator = marketData.iterator();
		while(iterator.hasNext()){
			MarketPo entry = iterator.next();
			MarketPo marketPo = entry;
			if(marketPo.getId().equals(marketId)){
				return marketPo;
			}
		}
		return null;
	}

	@Override
	public boolean updateMarket(MarketPo marketPo) {
		String marketId = marketPo.getId();
		Iterator<MarketPo> iterator = marketData.iterator();
		while(iterator.hasNext()){
			MarketPo entry = iterator.next();
			MarketPo market = entry;
			if(market.getId().equals(marketId)){
				marketData.add(marketPo);
				marketDataHelper.updateMarketData(marketData);
				return true;
			}
		}

		return false;

	}*/

}
