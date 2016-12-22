package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import interactor.hotel.HotelInfoInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.HotelCommentManagerImpl;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import presentation.hotel.manager.impl.HotelRoomManagerImpl;
import presentation.hotel.manager.impl.InfoManagerImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;

public class HotelInfoCourier implements HotelInfoInteractor {
	private static HotelInfoInteractor instance;

	public static HotelInfoInteractor launch(HotelInfoService handler, HotelAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelInfoCourier(handler, helper);
	}

	public static HotelInfoInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelInfoService handler;
	private HotelAccountService helper;

	private HotelInfoCourier(HotelInfoService handler, HotelAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}
	
	@Override
	@Title("Get Hotel Info")
	public void getHotelInfo() {
		String title = getTitle();

		HotelInfo info = execute(title, () -> {
			String name = getCurrentName();
			if (name != null)
				return handler.getHotelInfo(name);

			alertFail(title, "Not logged in yet");
			return null;
		});

		InfoManagerImpl.getInstance().setHotel(new HotelVoBuilder(info).getHotelInfo());
		
	}

	@Override
	@Title("Get Orders by Status")
	public void getHotelOrdersByStatus() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return SearchOrderAccessorImpl.getInstance().getStatus().stream().map(status -> handler.getHotelOrdersByStatus(id, status))
						.collect(Collectors.reducing((l1, l2) -> {
							l1.addAll(l2);
							return l1;
						})).get();

			alertFail(title, "Not logged in yet");
			return null;
		});

		HotelOrderManagerImpl.getInstance().setOrderList(list);
		
	}

	@Override
	@Title("Get Hotel Comments")
	public void getHotelComments() {
		String title = getTitle();

		List<CommentVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return handler.getHotelComment(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		HotelCommentManagerImpl.getInstance().setComment(list);
		
	}

	@Override
	@Title("Get Hotel Rooms")
	public void getHotelRooms() {
		String title = getTitle();

		Set<Room> set = execute(title, () -> {
			String name = getCurrentName();
			if (name!=null)
				return handler.getHotelInfo(name).getRooms();

			alertFail(title, "Not logged in yet");
			return null;
		});

		HotelRoomManagerImpl.getInstance().setRooms(set);
		
	}

	@Override
	@Title("Update Hotel Passwords")
	public void updatePassword() {
//		String title = getTitle();
//
//		execute(title, () -> {
//			int id = getCurrentId();
//			if (id != Integer.MIN_VALUE)
//
//				if (!handler.updatePassword(InfoModifyAccessorImpl.getInstance().getOldPasswordHash(),
//						InfoModifyAccessorImpl.getInstance().getNewPasswordHash()))
//					alertFail(title, "Wrong password");
//				else
//					alertSuccess(title, "Update password succeed");
//			return null;
//		});
		
	}

	@Override
	@Title("Get Hotel Basic Info")
	public void updateHotel() {
		String title = getTitle();
		
		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.updateInfo(InfoModifyAccessorImpl.getInstance().getModifyHotelInfo()))
					alertFail(title, "修改失败");
				else
					alertSuccess(title, "修改成功");
			return null;
		});
		
	}


	@Override
	@Title("Get Orders")
	public void getHotelAllOrders() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return handler.getHotelAllOrders(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		HotelOrderManagerImpl.getInstance().setOrderList(list);
		
	}
	
	
	private String getCurrentName() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null : curAcc.getName();
	}
	
	private int getCurrentId() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? Integer.MIN_VALUE : curAcc.getId();
	}

}
