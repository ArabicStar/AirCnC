package utils.info.member.credit;

import utils.info.member.credit.CreditChangeTemplate.ChangeResultType;

public class ChangeAction {
	protected ActionType actionType;
	protected ActionProperties properties;

	public ChangeAction() {
	}

	public ChangeAction(ActionType actionType) {
		this.actionType = actionType;
	}

	public ChangeAction(ChangeAction anAction) {
		this(anAction.getActionType());
		setPropeties(anAction.getProperties());
	}

	public void setPropeties(ActionProperties properties) {
		if (!checkProperties(properties))
			return;
		this.properties = new ActionProperties(properties);
	}

	public ActionProperties getProperties() {
		return new ActionProperties(properties);
	}

	public String getFormatString() {
		return actionType.getFormatString();
	}

	public ActionType getActionType() {
		return actionType;
	}

	public ChangeResultType getResultType() {
		return actionType.getResult();
	}

	public boolean validateChangeValue(int changeValue) {
		return actionType.validateChangeValue(changeValue);
	}

	public boolean checkProperties(ActionProperties properties) {
		switch (actionType) {
		case CHARGE:
			return Double.isFinite(properties.getDoubleVal1()) && properties.getDoubleVal1() > 0;
		default:
			return properties.getStrVal1() != null && properties.getStrVal2() != null;
		}
	}
}
