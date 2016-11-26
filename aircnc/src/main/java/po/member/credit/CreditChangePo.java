package po.member.credit;

import java.time.Instant;

import utils.info.member.MemberInfoTemplate;
import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfo;

/**
 * Po of credit change.<br>
 * <br>
 * <b>NOTICE:</b><br>
 * All public setter is for builder and hibernate, <b>DO NOT</b> use in client
 * code.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class CreditChangePo extends CreditChangeInfo {
	private long ccid;
	private int numMemId;

	/**
	 * Get identifier.<br>
	 * 
	 * @return ccid;
	 */
	public long getCcid() {
		return ccid;
	}

	/**
	 * Set identifier.<br>
	 * 
	 * @param id
	 */
	public void setCcid(long ccid) {
		this.ccid = ccid;
	}

	/**
	 * Set member id.<br>
	 * 
	 * @param memberId
	 *            member id string <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setMemberId(String memberId) {
		this.memberId = memberId;
		this.numMemId = MemberInfoTemplate.convertID2Num(memberId);
		return this;
	}

	public void setNumMemId(int numMemId) {
		this.memberId = MemberInfoTemplate.formatID(numMemId);
		this.numMemId = numMemId;
	}

	public int getNumMemId() {
		return numMemId;
	}

	/**
	 * Set time stamp.<br>
	 * 
	 * @param timeInstant
	 *            time stamp Instant instance <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setTimeInstant(Instant timeInstant) {
		this.timeInstant = timeInstant;
		return this;

	}

	/**
	 * Set before credit value.<br>
	 * 
	 * @param beforeCredit
	 *            before credit value <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setBeforeCredit(int beforeCredit) {
		this.beforeCredit = beforeCredit;
		return this;

	}

	/**
	 * Set after credit value.<br>
	 * 
	 * @param afterCredit
	 *            after credit value <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setAfterCredit(int afterCredit) {
		this.afterCredit = afterCredit;
		return this;

	}

	public void setTypeString(String type) {
		this.actionType = ActionType.valueOf(type);
	}

	/**
	 * Set action type.<br>
	 * 
	 * @param actionType
	 *            action type <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setActionType(ActionType actionType) {
		this.actionType = actionType;
		return this;
	}

	/**
	 * Set money.<br>
	 * 
	 * @param money
	 *            amount of money <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setMoney(int money) {
		this.money = money;
		return this;

	}

	/**
	 * Set order id.<br>
	 * 
	 * @param orderId
	 *            order id string <br>
	 * @return this instance <br>
	 */
	public CreditChangePo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;

	}
}
