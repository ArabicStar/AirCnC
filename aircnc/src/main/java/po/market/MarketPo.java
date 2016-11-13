package po.market;


import utils.info.market.MarketInfo;

public class MarketPo extends MarketInfo{

	protected int passwordHash;

	public MarketPo() {
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

	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}

	public MarketPo setID(String id) {
		this.id = id;
		return this;
	}

	public MarketPo setName(String name) {
		this.name = name;
		return this;
	}

	public MarketPo setPasswordHash(int passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}
}
