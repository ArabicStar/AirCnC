package utils.info.member.credit;

import java.time.Instant;

/**
 * Abstract of credit change info
 * 
 * @author ClevelandAlto
 *
 */
public abstract class CreditChangeInfo extends CreditChangeInfoTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049240588529083204L;

	/**
	 * Get member id.<br>
	 * 
	 * @return member id string
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * Get time stamp.<br>
	 * 
	 * @return time stamp
	 */
	public Instant getTimeInstant() {
		return timeInstant;
	}

	/**
	 * Get before credit.<br>
	 * 
	 * @return before credit value <br>
	 */
	public int getBeforeCredit() {
		return beforeCredit;
	}

	/**
	 * Get after credit.<br>
	 * 
	 * @return after credit value <br>
	 */
	public int getAfterCredit() {
		return afterCredit;
	}

	/**
	 * Get action type.<br>
	 * 
	 * @return action type <br>
	 */
	public ActionType getActionType() {
		return actionType;
	}

	public String getTypeString() {
		return actionType.name();
	}

	/**
	 * Get money.<br>
	 * 
	 * @return amount of money <br>
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Get order id.<br>
	 * 
	 * @return order id string <br>
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Get format string.<br>
	 * 
	 * @return format string <br>
	 */
	public String getFormatString() {
		return new StringBuilder(actionType.getFormatString()).append(STRING_CACHE).toString();
	}
}
