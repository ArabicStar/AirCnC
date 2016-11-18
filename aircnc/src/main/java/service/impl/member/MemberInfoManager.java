package service.impl.member;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.HotelDao;
import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import service.query.QueryService;
import utils.condition.CreditChangeCondition;
import utils.condition.OrderCondition;
import utils.info.member.MemberInfo;
import vo.HotelVo;
import vo.member.MemberVoBuilder;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public class MemberInfoManager implements MemberInfoService {
	private MemberDao memberDao;
	private HotelDao hotelDao;

	private QueryService queryService;
	private MemberAccountService accountService;

	public MemberInfoManager(QueryService queryService, MemberAccountService accountService, MemberDao memberDao,
			HotelDao hotelDao) {
		this.memberDao = memberDao;
		this.queryService = queryService;
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
		return queryService.findOrders(new OrderCondition());
	}

	@Override
	public List<HotelVo> getMemberHistoryHotel(String id) {
		if (!memberDao.existsMember(id))
			return null;

		// TODO need modified
		List<OrderVo> historyOrders = queryService.findOrders(new OrderCondition());
		List<HotelVo> historyHotels = historyOrders.parallelStream().map(OrderVo::getHotel).distinct()
				.map(Integer::valueOf).map(i -> hotelDao.getHotel(i))
				.map(hp -> new HotelVo(hp.getHotelName(), hp.getHotelName(), hp.getScope()))
				.collect(Collectors.toList());

		return historyHotels;
	}

	@Override
	public List<CreditChangeVo> getMemberCreditChange(String id) {
		if (!memberDao.existsMember(id))
			return null;

		// TODO need modified
		return queryService.findCreditChanges(new CreditChangeCondition());
	}

	@Override
	public boolean updateInfo(MemberInfo modifiedInfo) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Member Login");

		MemberPo po = (MemberPo) accountService.getLoginedMember();

		if (!modifiedInfo.getId().equals(po.getId()))
			throw new IllegalArgumentException("Incorresponding Member Info");

		return memberDao
				.updateMember(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
	}

}
