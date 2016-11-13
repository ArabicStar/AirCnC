package vo.market;

import utils.info.market.MarketInfo;
import utils.info.market.MarketInfoBuilder;

public class MarketVoBuilder extends MarketInfoBuilder{

	private static final MarketVo INVALID_MARKET_VO;
	static {
		INVALID_MARKET_VO = new MarketVo();
		INVALID_MARKET_VO.invalidate();
	}

	public static final MarketVo getInvalidInfo() {
		return INVALID_MARKET_VO;
	}

	public MarketVoBuilder(MarketInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}


	public MarketInfoBuilder setName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketVoBuilder setID(String id) {
		super.setID(id);
		return this;
	}

	@Override
	public MarketVo getMarketInfo() {
		if (!isReady())
			return null;

		return new MarketVo().setID(id).setName(name);
	}

}
