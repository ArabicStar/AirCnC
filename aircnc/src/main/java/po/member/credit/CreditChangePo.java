package po.member.credit;

import java.time.Instant;

import utils.info.member.MemberInfoTemplate;
import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfo;

/**
 * Po of credit change.<br>
 * Compared with CreditChangeVo, this po will be persisted in database, so an
 * identifier field {@code ccid} and some functional methods are added. You can
 * ignore them - just prepared for hibernate.<br>
 * <b>NOTICE:</b><br>
 * All public setter is for builder and hibernate, <b>DO NOT</b> use in client
 * code.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class CreditChangePo extends CreditChangeInfo {
	private int ccid;

	/**
	 * Set identifier.<br>
	 * 
	 * @param id
	 */
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}

	/**
	 * Get identifier.<br>
	 * 
	 * @return ccid;
	 */
	public int getCcid() {
		return ccid;
	}

	/**
	 * Set number member id, for hibernate.<br>
	 * 
	 * @param numMemId
	 *            number member id
	 */
	public void setNumMemId(int numMemId) {
		this.memberId = MemberInfoTemplate.formatID(numMemId);
	}

	/**
	 * Get number member id, for hibernate.<br>
	 * 
	 * @return number member id
	 */
	public int getNumMemId() {
		return MemberInfoTemplate.convertID2Num(memberId);
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
		return this;
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
