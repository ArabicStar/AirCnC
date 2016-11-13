package service.member;

import java.util.Collection;
import java.util.List;

import utils.info.member.MemberInfo;
import utils.info.member.credit.CreditChangeInfo;
import vo.HotelVo;
import vo.order.OrderVo;

public interface MemberInfoService {
	public MemberInfo getMemberInfo(String id);

	public List<OrderVo> getMemberOrder(String id);

	public List<HotelVo> getMemberHistoryHotel(String id);

	public List<CreditChangeInfo> getMemberCreditChange(String id);

	public boolean updateInfo(MemberInfo modifiedInfo);
}
