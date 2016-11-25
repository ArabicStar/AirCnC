package utils.info.market;


/**
 * Abstract of member info<br>
 * Immutable object.<br>
 * All setter parameters will be assumed checked already, so checks are skipped.
 * <br>
 * 
 * @author paranoia
 *
 */
public abstract class MarketInfo extends MarketInfoTemplate {
	
	/**
	 * mark a MarketInfo instance is valid or not. <br>
	 */
	protected boolean isValid;
	
	/**
	 * buffered blank string
	 */
	private static final String BLANK = "";
	
	/**
	 * Default initialization <br>
	 * <br>
	 * <b>Default value:</b>
	 * <ul>
	 * <li>String fields : blank string <br>
	 * <li>instance fields : null <br>
	 * <li>integer fileds : 0 <br>
	 * </ul>
	 * 
	 * @param type
	 */
	protected MarketInfo() {
		id = BLANK;
		name = BLANK;
		isValid = true;
	}
	
	/**
	 * Get id string. <br>
	 * 
	 * @return id string <br>
	 */
	public String getId() {
		if (isValid())
			return id;
		return null;
	}
	
	/**
	 * Get name string. <br>
	 * 
	 * @return name string <br>
	 */
	public abstract String getName();
	
	/**
	 * Get this instance validation status <br>
	 * 
	 * @return validation status <br>
	 */
	public boolean isValid() {
		return isValid;
	}
	
	/**
	 * make this instance invalid.<br>
	 */
	public void invalidate() {
		isValid = false;
	}
}
