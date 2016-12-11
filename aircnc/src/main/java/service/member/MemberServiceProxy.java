package service.member;

import static utils.exception.StaticExceptionFactory.*;
import java.util.List;

import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.hotel.HotelVo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public final class MemberServiceProxy extends AccessSecureProxy
		implements MemberAccountService, MemberCreditService, MemberInfoService {
	private static MemberServiceProxy instance;

	public static MemberServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberServiceProxy(clientId);
	}

	public static MemberServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private MemberAccountService accountService;
	private MemberInfoService infoService;
	private MemberCreditService creditService;

	private MemberServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 ***************************
	 * Actual manager loader
	 ***************************
	 */
	@AuthenticatePolicy({ Client.USER })
	public void loadAccountService(MemberAccountService accountService) {
		checkAuthentication();

		this.accountService = accountService;
	}

	@AuthenticatePolicy({ Client.USER })
	public void loadInfoService(MemberInfoService infoService) {
		checkAuthentication();

		this.infoService = infoService;
	}

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.MARKET })
	public void loadCreditService(MemberCreditService creditService) {
		checkAuthentication();

		this.creditService = creditService;
	}
	/* Actual manager loader */

	/*
	 ********************************************
	 * MemberAccountService method proxy
	 ********************************************
	 */
	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberInfo register(MemberVoBuilder newMember, int passwordHash) {
		checkAuthentication();

		return accountService.register(newMember, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberInfo login(String id, int passwordHash) {
		checkAuthentication();

		return accountService.login(id, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean logout() {
		checkAuthentication();

		return accountService.logout();
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean isLoggedin() {
		checkAuthentication();

		return accountService.isLoggedin();
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberInfo refreshCurrentAccount() {
		checkAuthentication();

		return accountService.refreshCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberInfo getCurrentAccount() {
		checkAuthentication();

		return accountService.getCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean existsMember(String id) {
		checkAuthentication();

		return accountService.existsMember(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public MemberInfo getMemberInfo(String id) {
		checkAuthentication();

		return infoService.getMemberInfo(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updateBasicInfo(MemberInfo modifiedInfo) {
		checkAuthentication();

		return infoService.updateBasicInfo(modifiedInfo);
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean updateAdvancedInfo(MemberInfo modifiedInfo) {
		checkAuthentication();

		return infoService.updateAdvancedInfo(modifiedInfo);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public boolean updatePassword(int oldPwdHash, int newPwdHash) {
		checkAuthentication();

		return infoService.updatePassword(oldPwdHash, newPwdHash);
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET })
	public List<OrderVo> getMemberAllOrders(String id) {
		checkAuthentication();

		return infoService.getMemberAllOrders(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<OrderVo> getMemberOrdersByStatus(String id, OrderStatus status) {
		checkAuthentication();

		return infoService.getMemberOrdersByStatus(id, status);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<HotelVo> getMemberHistoryHotels(String id) {
		checkAuthentication();

		return infoService.getMemberHistoryHotels(id);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<CreditChangeVo> getMemberCreditChange(String id) {
		checkAuthentication();

		return getMemberCreditChange(id);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MemberVo gainByCharge(int money, String memberId) {
		checkAuthentication();

		return creditService.gainByCharge(money, memberId);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public MemberVo gainByOrderExecution(OrderVo order) {
		checkAuthentication();

		return creditService.gainByOrderExecution(order);
	}

	@Override
	@AuthenticatePolicy({ Client.SERVER, Client.HOTEL })
	public MemberVo reduceByOverdue(OrderVo order) {
		checkAuthentication();

		return creditService.reduceByOverdue(order);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberVo reduceByCancel(OrderVo order) {
		checkAuthentication();

		return creditService.reduceByCancel(order);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public MemberVo recoverByDelay(OrderVo order) {
		checkAuthentication();

		return recoverByDelay(order);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MemberVo recoverByAppeal(OrderVo order) {
		checkAuthentication();

		return recoverByAppeal(order);
	}
}
