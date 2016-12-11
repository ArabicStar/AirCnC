package interactor.impl.member;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.stream.Collectors;

import interactor.member.MemberInfoInteractor;
import interactor.utils.Title;
import presentation.member.accessor.InfoModifyAccessor;
import presentation.member.accessor.SearchOrderInfoAccessor;
import presentation.member.manager.CreditChangeManager;
import presentation.member.manager.MyOrderManager;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.UserInfoManager;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVo;
import vo.member.credit.CreditChangeVo;
import vo.order.OrderVo;

public final class MemberInfoCourier implements MemberInfoInteractor {
	private static MemberInfoInteractor instance;

	public static MemberInfoInteractor launch(MemberInfoService handler, MemberAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MemberInfoCourier(handler, helper);
	}

	public static MemberInfoInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private MemberInfoService handler;
	private MemberAccountService helper;

	private MemberInfoCourier(MemberInfoService handler, MemberAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}

	@Override
	@Title("Get Member Info")
	public void getMemberInfo(UserInfoManager man) {
		String title = getTitle();

		MemberInfo info = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberInfo(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setUser(info);
	}

	@Override
	@Title("Get Orders")
	public void getMemberAllOrders(MyOrderManager man) {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberAllOrders(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setOrderList(list);
	}

	@Override
	@Title("Get Orders by Status")
	public void getMemberOrdersByStatus(SearchOrderInfoAccessor acs, MyOrderManager man) {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return acs.getStatus().stream().map(status -> handler.getMemberOrdersByStatus(id, status))
						.collect(Collectors.reducing((l1, l2) -> {
							l1.addAll(l2);
							return l1;
						})).get();

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setOrderList(list);
	}

	@Override
	@Title("Get History Hotels")
	public void getMemberHistoryHotel(SearchHotelManager man) {
		String title = getTitle();

		List<HotelVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberHistoryHotels(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setHotel(list);

	}

	@Override
	@Title("Get Credit Changes")
	public void getMemberCreditChange(CreditChangeManager man) {
		String title = getTitle();

		List<CreditChangeVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberCreditChange(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setCreditChanges(list);
	}

	@Override
	public void updatePassword(InfoModifyAccessor acs) {
		String title = getTitle();

		execute(title, () -> {
			String id = getCurrentId();
			if (id != null)

				if (!handler.updatePassword(acs.getPasswordHash(), acs.getPasswordHash()))
					alertFail(title, "Wrong password");
				else
					alertSuccess(title, "Update password succeed");
			return null;
		});
	}

	@Override
	public void updateInfo(InfoModifyAccessor acs, UserInfoManager man) {
		String title = getTitle();
		MemberInfo modified = acs.getModifiedMemberVo();

		boolean res = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.updateAdvancedInfo(modified);

			alertFail(title, "Not logged in yet");
			return null;
		});

		man.setUser(res ? modified : null);
	}

	private String getCurrentId() {
		MemberInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null : curAcc.getId();
	}
}
