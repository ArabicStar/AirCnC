package service.member;

import java.util.List;

import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public interface MemberInfoService {
	public MemberInfo getMemberInfo(String id);

	public List<OrderVo> getMemberAllOrders(String id);

	public List<OrderVo> getMemberOrdersByStatus(String id, OrderStatus status);

	public List<HotelVo> getMemberHistoryHotels(String id);

	public List<CreditChangeVo> getMemberCreditChange(String id);

	public boolean updateBasicInfo(MemberInfo modifiedInfo);

	public boolean updateAdvancedInfo(MemberInfo modifiedInfo);

	public boolean updatePassword(int oldPwdHash, int newPwdHash);
}
