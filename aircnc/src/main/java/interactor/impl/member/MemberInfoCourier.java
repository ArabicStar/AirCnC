package interactor.impl.member;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import interactor.member.MemberInfoInteractor;
import interactor.utils.Title;
import presentation.member.accessor.impl.InfoModifyAccessorImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.manager.impl.CreditChangeManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import presentation.member.manager.impl.SearchHotelManagerImpl;
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
	public void getMemberInfo() {
		String title = getTitle();

		MemberInfo info = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberInfo(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		MemberInfoManagerImpl.getInstance().setUser(info);
	}

	@Override
	@Title("Get Orders")
	public void getMemberAllOrders() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberAllOrders(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		MyOrderManagerImpl.getInstance().setOrderList(list);
	}

	@Override
	@Title("Get Orders by Status")
	public void getMemberOrdersByStatus() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null) {
				Optional<List<OrderVo>> op = SearchOrderInfoAccessorImpl.getInstance().getStatus().stream()
						.map(status -> handler.getMemberOrdersByStatus(id, status))
						.collect(Collectors.reducing((l1, l2) -> {
							l1.addAll(l2);
							return l1;
						}));
				return op.isPresent() ? op.get() : new ArrayList<>();
			}

			alertFail(title, "Not logged in yet");
			return null;
		});

		MyOrderManagerImpl.getInstance().setOrderList(list);
	}

	@Override
	@Title("Get History Hotels")
	public void getMemberHistoryHotel() {
		String title = getTitle();

		List<HotelVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberHistoryHotels(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		SearchHotelManagerImpl.getInstance().setHotel(list);

	}

	@Override
	@Title("Get Credit Changes")
	public void getMemberCreditChange() {
		String title = getTitle();

		List<CreditChangeVo> list = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getMemberCreditChange(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		CreditChangeManagerImpl.getInstance().setCreditChanges(list);
	}

	@Override
	@Title("修改密码")
	public void updatePassword() {
		String title = getTitle();

		execute(title, () -> {
			String id = getCurrentId();
			if (id != null)

				if (!handler.updatePassword(InfoModifyAccessorImpl.getInstance().getOldPasswordHash(),
						InfoModifyAccessorImpl.getInstance().getNewPasswordHash()))
					alertFail(title, "Wrong password");
				else
					alertSuccess(title, "修改密码成功！");
			return null;
		});
	}

	@Override
	public void updateInfo() {
		String title = getTitle();
		MemberInfo modified = InfoModifyAccessorImpl.getInstance().getModifiedMemberVo();

		boolean res = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.updateBasicInfo(modified);

			alertFail(title, "Not logged in yet");
			return false;
		});

		MemberInfoManagerImpl.getInstance().setUser(res ? modified : null);
	}

	private String getCurrentId() {
		MemberInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null : curAcc.getId();
	}
}
