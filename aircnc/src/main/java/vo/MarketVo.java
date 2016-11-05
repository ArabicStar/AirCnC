package vo;

import util.market.MarketInfo;
/**
 * VO of market<br>
 * Immutable<br>
 *
 * @author ParanoiaSun
 *
 */
public final class MarketVo extends MarketInfo {
	MarketVo() {
		super();
		isValid = true;
	}

	@Override
	public String getID() {
		if (isValid)
			return id;
		return null;
	}

	@Override
	public String getName() {
		if (isValid)
			return username;
		return null;
	}

	MarketVo setID(String id) {
		this.id = id;
		return this;
	}

	MarketVo setName(String name) {
		this.username = name;
		return this;
	}

	void invalidate() {
		isValid = false;
	}

	@Override
	public String toString() {
		return "Id:" + id + "\nname:" + username;
	}
}