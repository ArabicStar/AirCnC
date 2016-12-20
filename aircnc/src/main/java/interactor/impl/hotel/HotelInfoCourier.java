package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import interactor.hotel.HotelInfoInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.InfoManagerImpl;
import presentation.member.accessor.impl.SearchOrderInfoAccessorImpl;
import presentation.member.manager.impl.HotelPromotionManagerImpl;
import presentation.member.manager.impl.MemberInfoManagerImpl;
import presentation.member.manager.impl.MyOrderManagerImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;

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
	@Title("Get Hotel Info")
	public void getHotelAllPromotions() {
		String title = getTitle();
		Set<PromotionVo> promotions = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return handler.getHotelAllPromotions(id);

			alertFail(title, "Not logged in yet");
			return null;
		});
		
		HotelPromotionManagerImpl.getInstance().setPromotion(promotions);		
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

		MyOrderManagerImpl.getInstance().setOrderList(list);
		
	}

	@Override
	public void getHotelComments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getHotelRooms() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBasicInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRooms() {
		// TODO Auto-generated method stub
		
	}
	
	private String getCurrentName() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null : curAcc.getName();
	}
	
	private int getCurrentId() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? Integer.MIN_VALUE : curAcc.getId();
	}

	@Override
	public void getHotelActivePromotions() {
		// TODO Auto-generated method stub
		
	}

}
