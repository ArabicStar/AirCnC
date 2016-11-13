package vo.market;

import utils.info.market.MarketInfo;
/**
 *
 *
 * @author ParanoiaSun
 */
public class MarketVo extends MarketInfo{

	protected MarketVo(){
		super();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	MarketVo setID(String id) {
		this.id = id;
		return this;
	}

	MarketVo setName(String name) {
		this.name = name;
		return this;
	}



}