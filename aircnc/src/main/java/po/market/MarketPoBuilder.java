package po.market;

import utils.info.market.MarketInfo;
import utils.info.market.MarketInfoBuilder;

import static utils.exception.StaticExceptionFactory.builderNotReadyEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import org.apache.commons.lang.StringUtils;

/**
 * Builder or MarketPo<br>
 *
 * @author paranoia
 *
 */
@SuppressWarnings("serial")
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
	
	public MarketPoBuilder() {
		super();
	}

	public MarketPoBuilder(MarketInfo info) {
		super(info);
		if (!info.isValid())
			throw new IllegalArgumentException("Invalid MarketInfo Instance");

		setID(info.getId());
		String name = StringUtils.deleteWhitespace(info.getName());
		setName(name);
	}

	@Override
	public MarketPoBuilder setID(String id) {
		super.setID(id);
		return this;
	}

	public MarketPoBuilder setName(String name) {
		if (checkUserName(name))
			this.name = name;
		else
			throw illegalArgEx("Market's name");

		return this;
	}

	/**
	 * @param passwordHash
	 * @return this instance
	 */
	public MarketPoBuilder setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	@Override
	public MarketPo getMarketInfo() {
		if (!isReady() && passwordHash != Integer.MIN_VALUE)
			throw builderNotReadyEx();

		return new MarketPo().setId(id).setName(name).setPasswordHash(passwordHash);
	}
}
