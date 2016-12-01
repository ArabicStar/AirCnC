package vo.member.credit;

import java.time.Instant;

import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfo;

/**
 * Vo for credit change.<br>
 * Just add setter for builder. No public to ensure immutable.<br>
 * 
 * @author ClevelandAlto
 *
 */

public class CreditChangeVo extends CreditChangeInfo {
	/**
	 * Set member id.<br>
	 * 
	 * @param memberId
	 *            member id string <br>
	 * @return this instance <br>
	 */
	CreditChangeVo setMemberId(String memberId) {
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
	CreditChangeVo setTimeInstant(Instant timeInstant) {
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
	CreditChangeVo setBeforeCredit(int beforeCredit) {
		
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
	CreditChangeVo setAfterCredit(int afterCredit) {
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
	CreditChangeVo setActionType(ActionType actionType) {
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
	CreditChangeVo setMoney(int money) {
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
	CreditChangeVo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;

	}
}
