package service.query;

import java.util.List;

import utils.condition.*;
import vo.HotelVo;
import vo.OrderVo;
import vo.member.MemberVo;
import vo.member.credit.CreditChangeVo;

public interface QueryService {
	public List<CreditChangeVo> findCreditChanges(CreditChangeCondition searchCondition);

	public List<HotelVo> findHotels(HotelCondition searchCondition);

	public List<MemberVo> findMembers(MemberCondition searchCondition);

	public List<OrderVo> findOrders(OrderCondition searchCondition);
}
