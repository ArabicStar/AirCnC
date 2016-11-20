package service.impl.member;

import data.dao.CreditDao;
import data.dao.MemberDao;
import service.member.MemberCreditService;
import vo.member.MemberVo;
import vo.order.OrderVo;

public class CreditManager implements MemberCreditService {
	private MemberDao memberDao;
	private CreditDao creditDao;


	@Override
	public MemberVo gainByCharge(int money, String memberId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVo gainByOrderExecution(OrderVo order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVo reduceByOverdue(OrderVo order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVo reduceByCancel(OrderVo order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVo recoverByDelay(OrderVo order) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVo recoverByAppeal(OrderVo order) {
		// TODO 自动生成的方法存根
		return null;
	}

}
