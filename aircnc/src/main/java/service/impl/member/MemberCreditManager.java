package service.impl.member;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;

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

public final class MemberCreditManager implements MemberCreditService {
	private static MemberCreditService instance;

	public static MemberCreditService launch(MemberDao memberDao, CreditDao creditDao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new MemberCreditManager(memberDao, creditDao);
		return getInstance();
	}

	public static MemberCreditService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private MemberDao memberDao;
	private CreditDao creditDao;
	private CreditStrategy strategy;

	private MemberCreditManager(MemberDao memberDao, CreditDao creditDao) {
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

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberVo gainByOrderExecution(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.EXECUTED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(order.getMember().getId());
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_EXECUTION).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getExecutionIncrement(order)).getCreditChangeInfo();

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberVo reduceByOverdue(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.ABNORMAL)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(order.getMember().getId());
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_OVERDUE).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getOverdueReduction(order)).getCreditChangeInfo();

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberVo reduceByCancel(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.REPEALED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(order.getMember().getId());
		if (m == null)
			return null;

		if (LocalDateTime.now().plusHours(6).isBefore(order.getLastTime()))
			return new MemberVoBuilder(m).getMemberInfo();

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_CANCEL).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getCancelReduction(order)).getCreditChangeInfo();

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberVo recoverByDelay(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.EXECUTED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(order.getMember().getId());
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_DELAY).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getDelayRecovery(order)).getCreditChangeInfo();

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberVo recoverByAppeal(OrderVo order) {
		if (order == null)
			return null;

		if (order.getStatus() != OrderStatus.REPEALED)
			return MemberVoBuilder.invalidInfo();

		MemberPo m = getMember(order.getMember().getId());
		if (m == null)
			return null;

		CreditChangePo po = new CreditChangePoBuilder(m, ActionType.ORDER_APPEAL).setOrderId(order.getOrderId())
				.setCreditChange(strategy.getAppealRecovery(order)).getCreditChangeInfo();

		MemberPo changeCredit;
		if ((changeCredit = creditDao.changeCredit(po)) != null)
			return new MemberVoBuilder(changeCredit).getMemberInfo();

		return MemberVoBuilder.invalidInfo();
	}

	private MemberPo getMember(String id) {
		return memberDao.findMember(id);
	}
}
