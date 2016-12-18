package vo.market;

import org.apache.commons.lang.StringUtils;

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
	
	public MarketVoBuilder() {
		super();
	}

	public MarketVoBuilder(MarketInfo info) {
		super(info);
		if (!info.isValid())
			return;

		setID(info.getId());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}


	public MarketInfoBuilder setName(String name) {
		if (checkUserName(name))
			this.name = name;
		return this;
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
