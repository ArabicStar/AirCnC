package utils.info.member.credit;

import java.time.Instant;

import utils.info.member.MemberInfo;
import utils.info.member.MemberInfoTemplate;

public abstract class CreditChangeInfoBuilder extends CreditChangeTemplate {

	public CreditChangeInfoBuilder(MemberInfo member, ActionType actionType) {
		if (!member.isValid())
			throw new IllegalArgumentException("Invalid MemberInfo");
		this.memberId = member.getId();
		this.timeInstant = Instant.now();
		this.beforeCredit = member.getCredit();
		this.actionType = actionType;
	}

	public CreditChangeInfoBuilder setMoney(int money) {
		if (money > 0)
			this.money = money;
		return this;
	}

	public CreditChangeInfoBuilder setOrderId(String orderId) {
		if (orderId != null)// if(orderId!=null&&OrderInfo.checkId(orderId))
			this.orderId = orderId;
		return this;
	}

	public void setCreditChange(int delta) {
		if (!actionType.checkChangeValue(delta))
			return;

		afterCredit = beforeCredit + delta;
	}

	public boolean isReady() {
		return true;
		// return MemberInfoTemplate.checkID(memberId)&&
	}

	public abstract CreditChangeInfo getCreditChangeInfo();
}
