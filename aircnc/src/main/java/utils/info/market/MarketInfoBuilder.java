package utils.info.market;


/**
 * Abstract of builder for MarketInfo, assisting to assure immutable object.
 * <br>
 * <br>
 * <b>NOTICE:</b>
 * <ul>
 * <li>Default credit value is 0. Other field have <b>NO</b> default value. All
 * field should be set before build a MemberInfo instance.<br>
 * <li>All parameters of setter will be checked firstly. If invalid, the setting
 * will not take effect, and IllgealArgumentException will be thrown. <br>
 * </ul>
 * 
 * @author paranoia
 * @see utils.info.market.MarketInfo
 */

public abstract class MarketInfoBuilder extends MarketInfoTemplate{
	
	/**
	 * Initialize a builder by given type. <br>
	 * 
	 * @param type
	 */
	public MarketInfoBuilder() {
		
	}
	/**
	 * Initialize a builder by given MemberInfo, all information will be kept.
	 * <br>
	 * 
	 * @param info
	 */
	public MarketInfoBuilder(MarketInfo info) {
		this.setID(info.getId()).setName(info.getName());
	}
	
	/**
	 * Set id. <br>
	 * 
	 * @param id
	 *            id string <br>
	 * @return this instance <br>
	 */
	public MarketInfoBuilder setID(String id) {
		if (checkID(id))
			this.id = id;
		return this;
	}
	
	/**
	 * Set name. <br>
	 * 
	 * @param name
	 *            name string <br>
	 * @return this instance <br>
	 */
	public abstract MarketInfoBuilder setName(String name);
	
	/**
	 * Check if builder can build a MarketInfo instance, that is, all fields
	 * are valid. <br>
	 * 
	 * @return if all fields are valid <br>
	 */
	public boolean isReady() {
		return id != null && name != null;
	}

	public abstract MarketInfo getMarketInfo();
	
	public static int compareMarketInfo(final MarketInfo i1, final MarketInfo i2) {
		// different member
		if (!i1.getId().equals(i2.getId()))
			return -1;

		int flag = 0;
		// basic info
		if (!i1.getName().equals(i2.getName()))
			flag = flag | 1;

		return flag;
	}
}
