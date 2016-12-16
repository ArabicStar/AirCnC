package data.dao.impl.market;

import static data.hibernate.Hibernator.execute;
import data.dao.market.MarketDao;
import po.market.MarketPo;

/**
 * the implementation of MarketDao
 * @author paranoia
 *
 */
public enum MarketDaoImpl implements MarketDao{
	INSTANCE;

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
		return execute(session -> {
			Boolean flag = Boolean.FALSE;// for performance

			MarketPo deleted = (MarketPo) session.get(MarketPo.class, parseId(id));
			if (flag = Boolean.valueOf((deleted != null)))// check existence
				
				session.delete(deleted);

			return flag;
		});
	}

	@Override
	public boolean updateMarket(MarketPo po) {
		if(po==null){
			return false;
		}
		return execute(session -> {
			Boolean flag = Boolean.FALSE;
			
			MarketPo modified = (MarketPo) session.get(MarketPo.class, po.getId());
			if(flag = Boolean.valueOf(modified !=null))
				session.update(modified);
			
			return flag;
		});
	}

	@Override
	public MarketPo findMarket(String idString) {
		return execute(session -> {
			return (MarketPo) session.get(MarketPo.class, parseId(idString));
		});
	}

	@Override
	public boolean existsMarket(String idString) {
		return execute(session -> {
			return (MarketPo) session.get(MarketPo.class, parseId(idString)) != null;
		});
	}
	
	/* parse an id string. if invalid, throw IAE. */
	private static final int parseId(final String id) {
		if (!MarketPo.checkID(id))
			throw new IllegalArgumentException("Wrong ID");

		return Integer.parseInt(id);
	}


}
