package service.impl.member;

import java.util.List;

import data.dao.hotel.HotelDao;
import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import service.query.MemberInfoQueryService;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public class MemberInfoManager implements MemberInfoService, MemberInfoQueryService {
	private MemberDao memberDao;
	private HotelDao hotelDao;

	private MemberAccountService accountService;

	public MemberInfoManager(MemberAccountService accountService, MemberDao memberDao, HotelDao hotelDao) {
		this.memberDao = memberDao;
		this.hotelDao = hotelDao;
		this.accountService = accountService;
	}

	@Override
	public MemberInfo getMemberInfo(String id) {
		return new MemberVoBuilder(memberDao.findMember(id)).getMemberInfo();
	}

	@Override
	public List<OrderVo> getMemberOrder(String id) {
		// TODO need implemented
		return null;
	}

	@Override
	public List<HotelVo> getMemberHistoryHotel(String id) {
		// if (!memberDao.existsMember(id))
		// return null;

		// TODO need modified
		List<OrderVo> historyOrders = null;
		List<HotelVo> historyHotels = null;// =
		// historyOrders.parallelStream().map(OrderVo::getHotel).distinct()
		// .map(Integer::valueOf).map(i -> hotelDao.getHotel(i))
		// .map(hp -> new HotelVo(hp.getHotelName(), hp.getHotelName(),
		// hp.getScope()))
		// .collect(Collectors.toList());

		return historyHotels;
	}

	@Override
	public List<CreditChangeVo> getMemberCreditChange(String id) {
		// if (!memberDao.existsMember(id))
		// return null;

		// TODO need modified
		return null;
	}

	@Override
	public boolean updateInfo(MemberInfo modifiedInfo) {
		if (!accountService.isLogined())
			throw new IllegalStateException("MemberInfoServiceImpl.updateInfo - No Member is Logined");

		MemberPo po = (MemberPo) accountService.getCurrentAccount();

		if (!modifiedInfo.getId().equals(po.getId()))
			throw new IllegalArgumentException("MemberInfoServiceImpl.updateInfo - Incorresponding Member Info");

		return memberDao
				.updateMember(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
	}

	@Override
	public MemberInfo searchById(String id) {
		return getMemberInfo(id);
	}

	@Override
	public boolean updatePassword(int oldPwdHash, int newPwdHash) {
		if (!accountService.isLogined())
			throw new IllegalStateException("MemberInfoServiceImpl.updatePassword - No Member is Logined");

		MemberPo po = (MemberPo) accountService.getCurrentAccount();

		if (po.getPasswordHash() != oldPwdHash)
			return false;

		return memberDao.updateMember(new MemberPoBuilder(po).setPasswordHash(newPwdHash).getMemberInfo());
	}

}
