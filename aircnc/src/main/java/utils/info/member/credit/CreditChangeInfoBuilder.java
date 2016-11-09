package utils.info.member.credit;

import java.time.Instant;

import utils.info.member.MemberInfo;

public abstract class CreditChangeInfoBuilder extends CreditChangeTemplate {
	private ActionType actionType;
	private ActionProperties properties;

	public CreditChangeInfoBuilder(MemberInfo member, ActionType actionType) {
		this.memberID = member.getID();
		this.timeInstant = Instant.now();
		this.beforeCredit = member.getCredit();
		this.actionType = actionType;
	}

	public void setProperties(ActionProperties properties) {
		this.properties = new ActionProperties(properties);
	}

	public void setCreditChange(int delta) {
		if (!actionType.validateChangeValue(delta))
			return;

		afterCredit = beforeCredit + delta;
	}

	public abstract CreditChangeInfo getCreditChangeInfo();
}
