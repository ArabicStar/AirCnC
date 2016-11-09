package po.member.credit;

import utils.info.member.MemberInfo;
import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfoBuilder;

public class CreditChangePoBuilder extends CreditChangeInfoBuilder {

	public CreditChangePoBuilder(MemberInfo member, ActionType actionType) {
		super(member, actionType);
	}

	@Override
	public CreditChangePo getCreditChangeInfo() {
		return null;
	}

}
