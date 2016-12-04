package utils.info.member.credit;

import java.time.Instant;

import utils.info.member.MemberInfo;

/**
 * Abstract builder for CreditChangeInfo.<br>
 * <b>NOTICE:</b>
 * <ul>
 * <li><b>member id</b>, <b>current credit value</b> and <b>action type</b> have
 * to be initialized at first.
 * <li><b>timestamp</b> is auto generated.
 * <li><b>money</b>, <b>order id</b> and <b>credit change</b> can be set after.
 * <li>All fields are required before build a ContactInfo instance, except money
 * and order id. Requirement of these two fields depends on action type.<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class CreditChangeInfoBuilder extends CreditChangeInfoTemplate {

	/**
	 * Initialize a CreditChangeInfoBuilder from given MemberInfo and
	 * ActionType.<br>
	 * 
	 * @param member
	 *            MemberInfo instance <br>
	 * @param actionType
	 *            action type <br>
	 */
	public CreditChangeInfoBuilder(MemberInfo member, ActionType actionType) {
		if (!member.isValid())
			throw new IllegalArgumentException("CreditChangeInfoBuilder - Invalid MemberInfo");
		this.memberId = member.getId();
		this.timeInstant = Instant.now();
		this.beforeCredit = member.getCredit();
		this.actionType = actionType;
	}

	public CreditChangeInfoBuilder(CreditChangeInfo info) {
		this.memberId = info.getMemberId();
		this.actionType = info.getActionType();
		this.beforeCredit = info.getBeforeCredit();
		this.afterCredit = info.getAfterCredit();
		this.timeInstant = info.getTimeInstant();
		this.money = info.getMoney();
		this.orderId = info.getOrderId();
	}

	/**
	 * Set amount of money.<br>
	 * 
	 * @param money
	 *            amount of money <br>
	 * @return this instance <br>
	 */
	public CreditChangeInfoBuilder setMoney(int money) {
		if (actionType.isMoneyRelated() && money > 0)
			this.money = money;
		return this;
	}

	/**
	 * Set order id.<br>
	 * 
	 * @param orderId
	 *            order id <br>
	 * @return this instance <br>
	 */
	public CreditChangeInfoBuilder setOrderId(String orderId) {
		if (actionType.isOrderRelated() && orderId != null)// if(orderId!=null&&OrderInfo.checkId(orderId))
			this.orderId = orderId;
		return this;
	}

	/**
	 * Set credit change value, with sign.<br>
	 * 
	 * @param delta
	 *            credit change value, with sign <br>
	 * @return this instance <br>
	 */
	public CreditChangeInfoBuilder setCreditChange(int delta) {
		if (actionType.checkChangeValue(delta))
			afterCredit = beforeCredit + delta;

		return this;
	}

	/**
	 * Check if this builder has been ready for building a CreditChangeInfo
	 * instance. That is, all fields have been set properly.<br>
	 * 
	 * @return if ready or not <br>
	 */
	public boolean isReady() {
		// TODO need order id check
		return MemberInfo.checkID(memberId) && actionType.isMoneyRelated() ? money > 0 : true;
		// &&actionType.isOrderRelated()?OrderInfo.checkId(orderId):true;
	}

	/**
	 * Build a CreditChangeInfo instance.<br>
	 * 
	 * @return built CreditChangeInfo instance
	 * @throws IllegalStateException
	 *             not ready <br>
	 */
	public abstract CreditChangeInfo getCreditChangeInfo();
}
