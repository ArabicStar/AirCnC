package service.member;

import java.util.List;

import data.dao.member.MemberDaoProxy;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public abstract class MemberServiceProxy implements MemberAccountService, MemberCreditService, MemberInfoService {
	private MemberAccountService accountService;
	private MemberInfoService infoService;
	private MemberCreditService creditService;
	protected MemberDaoProxy dao;
	private MemberServiceAccess access;

	public MemberServiceProxy(MemberServiceAccess access, MemberDaoProxy dao) {
		this.access = access;
		this.dao = dao;
	}

	public abstract void loadAccountService();

	public void loadAccountService(MemberAccountService accountService) {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return;
		}

		this.accountService = accountService;
	}

	// FIXME:depend on other service module
	public abstract void loadInfoService();

	public void loadInfoService(MemberInfoService infoService) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return;
		}

		this.infoService = infoService;
	}

	public abstract void loadCreditService();

	public void loadCreditService(MemberCreditService creditService) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return;
		}

		this.creditService = creditService;
	}

	@Override
	public MemberInfo getMemberInfo(String id) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return null;
		}

		return infoService.getMemberInfo(id);
	}

	@Override
	public List<OrderVo> getMemberOrder(String id) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return null;
		}

		return infoService.getMemberOrder(id);
	}

	@Override
	public List<HotelVo> getMemberHistoryHotel(String id) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return null;
		}

		return infoService.getMemberHistoryHotel(id);
	}

	@Override
	public List<CreditChangeVo> getMemberCreditChange(String id) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return null;
		}

		return getMemberCreditChange(id);
	}

	@Override
	public boolean updateInfo(MemberInfo modifiedInfo) {
		if (!access.isInfoServiceAccessed()) {
			System.err.println("INFO ACCESS DENIED");
			return false;
		}

		return infoService.updateInfo(modifiedInfo);
	}

	@Override
	public MemberVo gainByCharge(int money, String memberId) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}

		return creditService.gainByCharge(money, memberId);
	}

	@Override
	public MemberVo gainByOrderExecution(OrderVo order) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}

		return creditService.gainByOrderExecution(order);
	}

	@Override
	public MemberVo reduceByOverdue(OrderVo order) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}
		return creditService.reduceByOverdue(order);
	}

	@Override
	public MemberVo reduceByCancel(OrderVo order) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}
		return creditService.reduceByCancel(order);
	}

	@Override
	public MemberVo recoverByDelay(OrderVo order) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}
		return recoverByDelay(order);
	}

	@Override
	public MemberVo recoverByAppeal(OrderVo order) {
		if (!access.isCreditServiceAccessed()) {
			System.err.println("CREDIT ACCESS DENIED");
			return null;
		}

		return recoverByAppeal(order);
	}

	@Override
	public MemberInfo register(MemberVoBuilder newMember, int passwordHash) {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return null;
		}

		return accountService.register(newMember, passwordHash);
	}

	@Override
	public MemberInfo login(String id, int passwordHash) {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return null;
		}

		return accountService.login(id, passwordHash);
	}

	@Override
	public boolean logout() {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return false;
		}

		return accountService.logout();
	}

	@Override
	public boolean isLogined() {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return false;
		}

		return accountService.isLogined();
	}

	@Override
	public void refreshCurrentAccount() {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return;
		}

		accountService.refreshCurrentAccount();
	}

	@Override
	public MemberInfo getCurrentAccount() {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return null;
		}

		return accountService.getCurrentAccount();
	}

	@Override
	public boolean existsMember(String id) {
		if (!access.isAccountServiceAccessed()) {
			System.err.println("ACCOUNT ACCESS DENIED");
			return false;
		}

		return accountService.existsMember(id);
	}
}
