package utils.info.member.credit;

import java.time.Instant;

public abstract class CreditChangeInfo extends CreditChangeTemplate {
	public String getMemberId() {
		return memberId;
	}

	public Instant getTimeInstant() {
		return timeInstant;
	}

	public int getBeforeCredit() {
		return beforeCredit;
	}

	public int getAfterCredit() {
		return afterCredit;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public int getMoney() {
		return money;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getFormatString() {
		return new StringBuilder(actionType.getFormatString()).append(STRING_CACHE).toString();
	}
}
