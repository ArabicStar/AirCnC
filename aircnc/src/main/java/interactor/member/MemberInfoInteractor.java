package interactor.member;

import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.accessor.SearchOrderInfoAccessor;
import presentation.member.manager.CreditChangeManager;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.UserInfoManager;

public interface MemberInfoInteractor {
	public void getMemberInfo(final UserInfoManager man);

	public void getMemberAllOrders(final MyOrderManager man);

	public void getMemberOrdersByStatus(final SearchOrderInfoAccessor acs, MyOrderManager man);

	public void getMemberHistoryHotel(final SearchHotelManager man);

	public void getMemberCreditChange(final CreditChangeManager man);

	public void updatePassword(final InfoModifyAccessor acs);

	public void updateInfo(final InfoModifyAccessor acs, UserInfoManager man);
}
