package service.impl.member;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.query.CreditQueryDao;
import po.member.MemberPo;
import po.member.credit.CreditChangePo;
import po.member.credit.CreditChangePoBuilder;
import service.member.CreditStrategy;
import service.member.MemberCreditService;
import service.query.CreditChangeQueryService;
import utils.info.member.MemberInfoTemplate;
import utils.info.member.credit.ActionType;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.member.credit.CreditChangeVoBuilder;
import vo.order.OrderVo;

public class MemberCreditManager implements MemberCreditService, CreditChangeQueryService {
	private CreditQueryDao dao;
	private MemberDao memberDao;
	private CreditDao creditDao;
	private CreditStrategy strategy;

	public MemberCreditManager(MemberDao memberDao, CreditDao creditDao, CreditQueryDao creditQuery) {
		this.memberDao = memberDao;
		this.creditDao = creditDao;
		this.strategy = new SimpleCreditStrategyAdapter();
	}

	public MemberCreditManager(MemberDao memberDao, CreditDao creditDao, CreditQueryDao creditQuery,
			CreditStrategy strategy) {
		this(memberDao, creditDao, creditQuery);
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

	@Override
	public List<CreditChangeVo> searchByMemberId(String memberId) {
		if (!MemberInfoTemplate.checkID(memberId))
			throw new IllegalArgumentException("CreditChangeQueryServiceImpl.searchByMemberId - Invalid Member Id");

		List<CreditChangePo> poList = dao.searchByMemberId(memberId);
		if (poList == null)
			return null;

		return poList.stream().map(po -> new CreditChangeVoBuilder(po).getCreditChangeInfo())
				.collect(Collectors.toList());
	}

	private MemberPo getMember(String id) {
		return memberDao.findMember(id);
	}
}
