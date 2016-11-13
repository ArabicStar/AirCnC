package service.impl.member;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.HotelDao;
import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberInfoService;
import service.query.QueryService;
import utils.condition.CreditChangeCondition;
import utils.condition.OrderCondition;
import utils.info.member.MemberInfo;
import utils.info.member.credit.CreditChangeInfo;
import vo.HotelVo;
import vo.OrderVo;
import vo.member.MemberVoBuilder;

public class InfoManager implements MemberInfoService {
	private MemberDao memberDao;
	private QueryService queryService;
	private HotelDao hotelDao;

	public InfoManager(MemberDao memberDao, QueryService service, HotelDao hotelDao) {
		this.memberDao = memberDao;
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
	public List<CreditChangeInfo> getMemberCreditChange(String id) {
		if (!memberDao.existsMember(id))
			return null;

		// TODO need modified
		return queryService.findCreditChanges(new CreditChangeCondition());
	}

	@Override
	public boolean updateInfo(MemberInfo modifiedInfo) {
		MemberPo po = memberDao.findMember(modifiedInfo.getID());
		if (po == null)
			return false;

		return memberDao
				.updateMember(new MemberPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getMemberInfo());
	}

}
