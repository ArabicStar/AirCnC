package po.member.credit;

import utils.info.member.MemberInfo;
import utils.info.member.credit.ActionType;
import utils.info.member.credit.CreditChangeInfoBuilder;

/**
 * Builder for CreditChangePo.<br>
 * 
 * @author ClevelandAlto
 *
 */
public class CreditChangePoBuilder extends CreditChangeInfoBuilder {

	/**
	 * Default initialization, work the same as parent class.<br>
	 * 
	 * @param member
	 *            MemberInfo instance <br>
	 * @param actionType
	 *            action type <br>
	 */
	public CreditChangePoBuilder(MemberInfo member, ActionType actionType) {
		super(member, actionType);
	}

	@Override
	public CreditChangePoBuilder setCreditChange(int delta) {
		super.setCreditChange(delta);
		return this;
	}

	@Override
	public CreditChangePoBuilder setMoney(int money) {
		super.setMoney(money);
		return this;
	}

	@Override
	public CreditChangePoBuilder setOrderId(String orderId) {
		super.setOrderId(orderId);
		return this;
	}

	@Override
	public CreditChangePo getCreditChangeInfo() {
		if (!isReady())
			throw new IllegalStateException("CreditChangePoBuilder - Lack of Info");

		return new CreditChangePo().setActionType(actionType).setBeforeCredit(beforeCredit).setAfterCredit(afterCredit)
				.setMemberId(memberId).setOrderId(orderId).setTimeInstant(timeInstant);
	}

}
