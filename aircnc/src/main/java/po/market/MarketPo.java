package po.market;


import org.apache.commons.lang.StringUtils;

import utils.info.market.MarketInfo;

/**
 * Po for Market.<br>
 * The public setters is only for builder and hibernate. Please <b>NOT</b> used
 * in client code<br>
 * 
 * @author paranoia
 *
 */

public class MarketPo extends MarketInfo{
	protected int passwordHash;
	protected int numId = Integer.MIN_VALUE;

	protected MarketPo() {
		super();
	}

	@Override
	public String getName() {
		if (isValid())
			return StringUtils.deleteWhitespace(this.name);
		return null;
	}

	public int getPasswordHash() {
		if (isValid())
			return this.passwordHash;
		return Integer.MIN_VALUE;
	}

	public int getNumId() {
		return numId;
	}


	/**
	 * Set id of an integer.<br>
	 * Only for hibernate.<br>
	 * 
	 * @param numId
	 */
	public void setNumId(int numId) {
		this.numId = numId;
		this.id = formatID(numId);
	}

	/**
	 * Set id of a string.<br>
	 * Only for hibernate and builder.<br>
	 * 
	 * @param id
	 * @return this instance
	 */
	public MarketPo setId(String id) {
		this.id = id;
		this.numId = Integer.valueOf(id);
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
