package util.market;

public abstract class MarketInfo{

	protected boolean isValid;

	protected String id;
	protected String username;

	private static final String BLANK = "";

	protected MarketInfo() {
		this.id = BLANK;
		this.username = BLANK;
		isValid = true;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return username;
	}

	public boolean isValid() {
		return isValid;
	}
}
