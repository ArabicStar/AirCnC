package service.member;

import java.util.List;

import utils.info.member.MemberInfo;
import vo.hotel.HotelVo;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public interface MemberInfoService {
	public MemberInfo getMemberInfo(String id);

	public List<OrderVo> getMemberOrder(String id);

	public List<HotelVo> getMemberHistoryHotel(String id);

	public List<CreditChangeVo> getMemberCreditChange(String id);

	public boolean updateInfo(MemberInfo modifiedInfo);
}
