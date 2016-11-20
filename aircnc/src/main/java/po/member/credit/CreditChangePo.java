package po.member.credit;

import java.time.Instant;

import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfo;

public class CreditChangePo extends CreditChangeInfo {
	public CreditChangePo setMemberId(String memberId) {
		this.memberId = memberId;
		return this;

	}

	public CreditChangePo setTimeInstant(Instant timeInstant) {
		this.timeInstant = timeInstant;
		return this;

	}

	public CreditChangePo setBeforeCredit(int beforeCredit) {
		this.beforeCredit = beforeCredit;
		return this;

	}

	public CreditChangePo setAfterCredit(int afterCredit) {
		this.afterCredit = afterCredit;
		return this;

	}

	public CreditChangePo setActionType(ActionType actionType) {
		this.actionType = actionType;
		return this;

	}

	public CreditChangePo setMoney(int money) {
		this.money = money;
		return this;

	}

	public CreditChangePo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;

	}
}
