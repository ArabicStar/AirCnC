package po.market;

import utils.info.market.MarketInfo;
import utils.info.market.MarketInfoBuilder;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;

/**
 * Builder or MarketPo<br>
 *
 * @author paranoia
 *
 */
public class MarketPoBuilder extends MarketInfoBuilder {
	
	private int passwordHash = Integer.MIN_VALUE;

	private static final MarketPo INVALID_MARKET_PO;
	static {
		INVALID_MARKET_PO = new MarketPo();
		INVALID_MARKET_PO.invalidate();
	}

	public static final MarketInfo getInvalidInfo() {
		return INVALID_MARKET_PO;
	}


	public MarketPoBuilder(MarketInfo info) {
		super(info);
		if (!info.isValid())
			return ;

		setID(info.getId());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}

	@Override
	public MarketInfoBuilder setID(String id) {
		super.setID(id);
		return this;
	}

	public MarketPoBuilder setName(String name) {
		if (checkUserName(name))
			// insert blank space to avoid injection attack
			this.name = name.replaceAll("(.{1})", "$1 ");

		return this;
	}

	public MarketPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	@Override
	public MarketPo getMarketInfo() {
		if (!isReady())
			return null;

		return new MarketPo().setId(id).setName(name);
	}
}
