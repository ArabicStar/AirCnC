package utils.info.member.credit;

import java.time.Instant;

/**
 * Abstract of credit change info object.<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class CreditChangeTemplate {
	/**
	 * Cache of format string <br>
	 */
	protected static final String STRING_CACHE = "信用值%d";

	/**
	 * related member's id
	 */
	protected String memberId;

	/**
	 * time stamp of change
	 */
	protected Instant timeInstant;

	/**
	 * action type of change
	 */
	protected ActionType actionType;

	/**
	 * money amount charged
	 */
	protected int money;

	/**
	 * relative order id
	 */
	protected String orderId;
	/**
	 * member's credit before change
	 */
	protected int beforeCredit = Integer.MIN_VALUE;

	/**
	 * member's credit after change
	 */
	protected int afterCredit = Integer.MIN_VALUE;
}
