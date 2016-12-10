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

/**
 * Implemention of MemberInfoService and MemberQueryService.<br>
 * Singleton.<br>
 * 
 * @see service.member.MemberInfoService
 * @see service.query.MemberQueryService
 * @author ClevelandAlto
 *
 */
public final class MemberInfoManager implements MemberInfoService, MemberQueryService {
	/****** singleton ******/
	private static MemberInfoManager instance;

	/**
	 * Singleton instance initializer.<br>
	 * Parameters are daos or services depended on in some methods. They are
	 * alternative, <b>null</b> is allowed. In that case, when call methods
	 * which need the null dependency, {@code IllegalStateException} will be
	 * thrown to notify a unsupported operation is attempted.<br>
	 * Specific dependencies of each methods will be explained in methods'
	 * comments.<br>
	 * 
	 * @param memberDao
	 *            dao member
	 * @param accountService
	 *            account service
	 * @param creditQueryService
	 *            credit query service
	 * @param orderQueryService
	 *            order query service
	 * @param hotelQueryService
	 *            hotel query service
	 * @return initialized instance
	 * 
	 * @throws IllegalStateException
	 *             singleton has existed already <br>
	 */
	public static MemberInfoManager launch(final MemberDao memberDao, final MemberAccountService accountService,
			final CreditQueryService creditQueryService, final OrderQueryService orderQueryService,
			final HotelQueryService hotelQueryService) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberInfoManager(memberDao, accountService, creditQueryService, orderQueryService,
				hotelQueryService);
	}

	/**
	 * Get singleton instance.<br>
	 * 
	 * @return singleton instance
	 * @throws IllegalStateException
	 *             if singleton doesn't exist yet <br>
	 */
	public static MemberInfoManager getInstance() {
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

		final MemberPo po = memberDao.findMember(id);

		return po == null ? null : new MemberVoBuilder(memberDao.findMember(id)).getMemberInfo();
	}

	/* MemberQueryService */

	@Override
	public boolean updateBasicInfo(MemberInfo modifiedInfo) {
		if (accountService == null || memberDao == null)
			throw unsupportedOpEx("update member basic info");

		if (modifiedInfo == null || !modifiedInfo.isValid())
			throw illegalArgEx("null or invalid member info");

		if (!accountService.isLogined())
			throw illegalStateException("Not logged in yet");

		MemberPo po = (MemberPo) accountService.getCurrentAccount();

		if (po == null)
			return false;

		int comp = MemberVoBuilder.compareMemberInfo(modifiedInfo, po);

		// different member with currently logged in account: denied
		if (comp == -1)
			throw inconsistentStatusEx();

		// modify advanced info: denied
		if ((comp & 2) != 0)
			throw unsupportedOpEx("update advanced member info");

		// no modification: return
		if ((comp & 1) == 0)
			return true;

		return updateInfo(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
	}

	/* MemberQueryService */

	@Override
	public boolean updateAdvancedInfo(MemberInfo modifiedInfo) {
		if (memberDao == null)
			throw unsupportedOpEx("update member advanced info");

		if (modifiedInfo == null || !modifiedInfo.isValid())
			throw illegalArgEx("null or invalid member info");

		final MemberPo po = memberDao.findMember(modifiedInfo.getId());

		if (po == null)// not exist
			return false;

		int comp = MemberVoBuilder.compareMemberInfo(modifiedInfo, po);

		// modify basic info: denied
		if ((comp & 1) != 0)
			throw unsupportedOpEx("update basic member info");

		// no modification: return
		if ((comp & 2) == 0)
			return true;

		return updateInfo(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
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

		return updateInfo(new MemberPoBuilder(po).setPasswordHash(newPwdHash).getMemberInfo());
	}

	private boolean updateInfo(MemberPo modifiedInfo) {
		return memberDao.updateMember(modifiedInfo);
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
}
