package utils.info.market;


/**
 * Abstract of member info<br>
 * to implement immutable object, a;ll setters are added in specific subclass
 *
 * @author ClevelandAlto
 *
 */
public abstract class MarketInfo extends MarketInfoTemplate {

	protected boolean isValid;

	private static final String BLANK = "";

	protected MarketInfo() {
		id = BLANK;
		name = BLANK;
		isValid = true;
	}

	public String getId() {
		if (isValid())
			return id;
		return null;
	}

	public String getName(){
		if (isValid())
			return name;
		return null;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}
}
