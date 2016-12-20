package utils.info.member;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Abstract of member info<br>
 * Immutable object.<br>
 * All setter parameters will be assumed checked already, so checks are skipped.
 * <br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class MemberInfo extends MemberInfoTemplate implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3515903786389626015L;

	/**
	 * mark a MemberInfo instance is valid or not. <br>
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
	protected MemberInfo(Type type) {
		this.type = type;
		id = BLANK;
		name = BLANK;
		credit = 0;
		contact = null;
		birthday = null;
		enterprise = BLANK;
		isValid = true;
	}

	/**
	 * Get type string. <br>
	 * 
	 * @return type string <br>
	 */
	public String getType() {
		if (isValid())
			return type.name().toLowerCase();
		return null;
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
	 * Get credit value. <br>
	 * 
	 * @return credit value <br>
	 */
	public int getCredit() {
		if (isValid())
			return credit;
		return Integer.MIN_VALUE;
	}

	/**
	 * Get contact info. <br>
	 * 
	 * @return ContactInfo instance <br>
	 */
	public ContactInfo getContact() {
		if (isValid())
			return contact;
		return null;
	}

	/**
	 * Get bitrhday date.<br>
	 * 
	 * @return birthday LocalDate instance <br>
	 */
	public abstract LocalDate getBirthday();

	/**
	 * Get enterprise name string.<br>
	 * 
	 * @return enterprise name string <br>
	 */
	public abstract String getEnterprise();

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
