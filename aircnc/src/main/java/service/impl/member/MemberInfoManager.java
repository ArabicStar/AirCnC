package service.impl.member;

import static utils.exception.StaticExceptionFactory.*;
import java.util.List;
import java.util.stream.Collectors;

import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import service.query.CreditQueryService;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import service.query.OrderQueryService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public final class MemberInfoManager implements MemberInfoService, MemberQueryService {
	/****** singleton ******/
	private static MemberInfoService instance;

	public static MemberInfoService launch(MemberDao memberDao, MemberAccountService accountService,
			CreditQueryService creditQueryService, OrderQueryService orderQueryService,
			HotelQueryService hotelQueryService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberInfoManager(memberDao, accountService, creditQueryService, orderQueryService,
				hotelQueryService);
	}

	public static MemberInfoService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private MemberDao memberDao;

	private MemberAccountService accountService;
	private CreditQueryService creditQueryService;
	private OrderQueryService orderQueryService;
	private HotelQueryService hotelQueryService;

	private MemberInfoManager(MemberDao memberDao, MemberAccountService accountService,
			CreditQueryService creditQueryService, OrderQueryService orderQueryService,
			HotelQueryService hotelQueryService) {
		this.memberDao = memberDao;
		this.accountService = accountService;
		this.creditQueryService = creditQueryService;
		this.orderQueryService = orderQueryService;
		this.hotelQueryService = hotelQueryService;
	}

	/*
	 ******************************************************
	 * As follow are MemberInfoService Implementions
	 ******************************************************
	 */
	@Override
	public MemberInfo getMemberInfo(final String id) {
		if (memberDao == null)
			throw unsupportedOpEx("get member info");

		if (!MemberInfo.checkID(id))
			throw illegalArgEx("Member id");

		return new MemberVoBuilder(memberDao.findMember(id)).getMemberInfo();
	}

	@Override
	public boolean updateInfo(MemberInfo modifiedInfo) {
		if (accountService == null || memberDao == null)
			throw unsupportedOpEx("update member info");

		if (!accountService.isLogined())
			throw illegalStateException("Not logged in yet");

		MemberPo po = (MemberPo) accountService.getCurrentAccount();

		if (!modifiedInfo.getId().equals(po.getId()))
			throw inconsistentStatusEx();

		return memberDao
				.updateMember(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
	}

	@Override
	public boolean updatePassword(int oldPwdHash, int newPwdHash) {
		if (accountService == null || memberDao == null)
			throw unsupportedOpEx("update password");

		if (!accountService.isLogined())
			throw illegalStateException("Not logged in yet");

		MemberPo po = (MemberPo) accountService.getCurrentAccount();

		if (po.getPasswordHash() != oldPwdHash)
			return false;

		return memberDao.updateMember(new MemberPoBuilder(po).setPasswordHash(newPwdHash).getMemberInfo());
	}

	/* Buffered member order query service */
	private String bufferedId = null;
	private List<OrderVo> bufferedOrderList;
	private boolean dirtyBuffer = false;

	@Override
	public List<OrderVo> getMemberAllOrders(final String id) {
		if (orderQueryService == null)
			throw unsupportedOpEx("get member orders");

		if (!MemberInfo.checkID(id))
			throw illegalArgEx("Member id");

		/* different id from buffered one */
		if (bufferedId == null || !bufferedId.equals(id)) {
			// get
			List<OrderVo> res = orderQueryService.getMemberOrders(id);

			// given id not exists, return
			if (res == null)
				return null;

			// exists, refresh buffer
			bufferedId = id;
			bufferedOrderList = res;
			dirtyBuffer = false;

			return bufferedOrderList;
		}

		/* same id with buffered one */
		// reverse buffer dirty indicator
		if (dirtyBuffer = !dirtyBuffer)
			return bufferedOrderList;// return buffered list
		else
			// refresh buffer, same id with buffer assure existence of given id,
			// not need to check again
			return bufferedOrderList = orderQueryService.getMemberOrders(id);
	}

	@Override
	public List<OrderVo> getMemberOrdersByStatus(String id, OrderStatus status) {
		if (orderQueryService == null)
			throw unsupportedOpEx("get member orders by status");

		return bufferedOrderList.stream().filter(o -> o.getStatus() == status).collect(Collectors.toList());
	}

	@Override
	public List<HotelVo> getMemberHistoryHotels(String id) {
		if (orderQueryService == null)
			throw unsupportedOpEx("get member history hotels");

		if (!MemberInfo.checkID(id))
			throw illegalArgEx("Member id");

		/* different id from buffered one */
		if (bufferedId == null || !bufferedId.equals(id)) {
			// get
			List<OrderVo> res = orderQueryService.getMemberOrders(id);

			// given id not exists, return
			if (res == null)
				return null;

			// exists, refresh buffer
			bufferedId = id;
			bufferedOrderList = res;
			dirtyBuffer = false;
		}

		return bufferedOrderList.stream().map(o -> hotelQueryService.findById(o.getHotelId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<CreditChangeVo> getMemberCreditChange(String id) {
		if (creditQueryService == null)
			throw unsupportedOpEx("get member credit change");
		if (!MemberVo.checkID(id))
			throw illegalArgEx("Member Id");

		return creditQueryService.searchByMemberId(id);
	}
	/* MemberInfoService */

	/*
	 ********************************************************
	 * As follow are MemberQueryService implementions
	 ********************************************************
	 */
	@Override
	public MemberInfo searchById(String id) {
		return getMemberInfo(id);
	}
	/* MemberQueryService */
}
