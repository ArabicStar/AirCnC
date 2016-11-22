package service.query;

import java.util.List;

import utils.condition.*;
import vo.hotel.HotelVo;
import vo.member.MemberVo;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public interface QueryService {
	public List<CreditChangeVo> findCreditChanges(CreditChangeCondition searchCondition);

	public List<HotelVo> findHotels(HotelCondition searchCondition);

	public List<MemberVo> findMembers(MemberCondition searchCondition);

	public List<OrderVo> findOrders(OrderCondition searchCondition);
}
