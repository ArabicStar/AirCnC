package po;

import util.market.MarketInfo;

public class MarketPo extends MarketInfo {
	private String passwordHash;

	MarketPo() {
	}


	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}


	MarketPo setId(String id) {
		this.id = id;
		return this;
	}

	MarketPo setName(String name) {
		this.username = name;
		return this;
	}

	MarketPo setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}
}
