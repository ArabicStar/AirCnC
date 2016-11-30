package service.impl.member;

import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import po.member.credit.CreditChangePoBuilder;
import service.member.CreditStrategy;
import service.member.MemberCreditService;
import utils.info.member.credit.ActionType;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.order.OrderVo;

public class MemberCreditManager implements MemberCreditService {
	private MemberDao memberDao;
	private CreditDao creditDao;
	private CreditStrategy strategy;

	public MemberCreditManager(MemberDao memberDao, CreditDao creditDao) {
		this.memberDao = memberDao;
		this.creditDao = creditDao;
		this.strategy = new SimpleCreditStrategyAdapter();
	}

	public MemberCreditManager(MemberDao memberDao, CreditDao creditDao, CreditStrategy strategy) {
		this(memberDao, creditDao);
		this.strategy = strategy;
	}

	public void setStrategy(CreditStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public MemberVo gainByCharge(int money, String memberId) {
		MemberPo m = getMember(memberId);
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.CHARGE).setMoney(money)
				.setCreditChange(strategy.getChargeIncrement(money)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	@Override
	public MemberVo gainByOrderExecution(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.UNEXECUTED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(MemberVo.formatID(order.getUserId()));
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_EXECUTION).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getExecutionIncrement(order)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	@Override
	public MemberVo reduceByOverdue(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.UNEXECUTED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(MemberVo.formatID(order.getUserId()));
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_OVERDUE).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getOverdueReduction(order)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	@Override
	public MemberVo reduceByCancel(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.UNEXECUTED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(MemberVo.formatID(order.getUserId()));
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_CANCEL).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getCancelReduction(order)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	@Override
	public MemberVo recoverByDelay(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.ABNORMAL)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(MemberVo.formatID(order.getUserId()));
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_DELAY).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getDelayRecovery(order)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	@Override
	public MemberVo recoverByAppeal(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.ABNORMAL)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(MemberVo.formatID(order.getUserId()));
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_APPEAL).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getAppealRecovery(order)).getCreditChangeInfo();

		return new MemberVoBuilder(creditDao.changeCredit(po)).getMemberInfo();
	}

	private MemberPo getMember(String id) {
		return memberDao.findMember(id);
	}
}
