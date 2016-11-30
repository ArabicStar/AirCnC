package vo.member.credit;

import utils.info.member.MemberInfo;
import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfoBuilder;

public class CreditChangeVoBuilder extends CreditChangeInfoBuilder {

	/**
	 * Default initialization, work the same as parent class.<br>
	 * 
	 * @param member
	 *            MemberInfo instance <br>
	 * @param actionType
	 *            action type <br>
	 */
	public CreditChangeVoBuilder(MemberInfo member, ActionType actionType) {
		super(member, actionType);
	}

	@Override
	public CreditChangeVoBuilder setCreditChange(int delta) {
		super.setCreditChange(delta);
		return this;
	}

	@Override
	public CreditChangeVoBuilder setMoney(int money) {
		super.setMoney(money);
		return this;
	}

	@Override
	public CreditChangeVoBuilder setOrderId(String orderId) {
		super.setOrderId(orderId);
		return this;
	}

	@Override
	public CreditChangeVo getCreditChangeInfo() {
		if (!isReady())
			throw new IllegalStateException("CreditChangeVoBuilder - Lack of Info");

		return new CreditChangeVo().setActionType(actionType).setBeforeCredit(beforeCredit).setAfterCredit(afterCredit)
				.setMemberId(memberId).setOrderId(orderId).setTimeInstant(timeInstant);
	}

}
