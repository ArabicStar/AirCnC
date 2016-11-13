package utils.info.market;


public abstract class MarketInfoBuilder extends MarketInfoTemplate{

	public MarketInfoBuilder(MarketInfo info) {
		this.setID(info.getId()).setName(info.getName());
	}

	public MarketInfoBuilder setID(String id) {
		if (checkID(id))
			this.id = id;
		return this;
	}

	public abstract MarketInfoBuilder setName(String name);

	public boolean isReady() {
		return id != null && name != null;
	}

	public abstract MarketInfo getMarketInfo();
}
