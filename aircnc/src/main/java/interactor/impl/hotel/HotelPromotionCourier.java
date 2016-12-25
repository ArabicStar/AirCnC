package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import interactor.hotel.HotelPromotionInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.impl.HotelPromotionAccessorImpl;
import presentation.hotel.manager.impl.HotelPromotionManagerImpl;
import service.hotel.HotelAccountService;
import service.promotion.HotelPromotionManagementService;
import utils.info.hotel.HotelInfo;
import vo.promotion.PromotionVo;

public class HotelPromotionCourier implements HotelPromotionInteractor{
	private static HotelPromotionInteractor instance;

	public static HotelPromotionInteractor launch(HotelPromotionManagementService handler,HotelAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionCourier(handler,helper);
	}

	public static HotelPromotionInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelPromotionManagementService handler;
	private HotelAccountService helper;

	private HotelPromotionCourier(HotelPromotionManagementService handler,HotelAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}
	
	@Override
	@Title("修改酒店促销策略")
	public void update() {
		String title = getTitle();

		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.updateHotelPromotion(HotelPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "修改酒店促销策略失败");
				else
					alertSuccess(title, "修改酒店促销策略成功");
			return null;
		});
	}

	@Override
	@Title("新增促销策略")
	public void addNew() {
		String title = getTitle();

		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.addHotelPromotion(HotelPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "新增酒店促销策略失败");
				else
					alertSuccess(title, "新增酒店促销策略成功");
			return null;
		});
	}

	@Override
	@Title("删除促销策略")
	public void delete() {
		String title = getTitle();

		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.deleteHotelPromotion(HotelPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "删除酒店促销策略失败");
				else
					alertSuccess(title, "删除酒店促销策略成功");
			return null;
		});
	}

	@Override
	@Title("获取促销策略")
	public boolean getHotelActivePromotion() {
		String title = getTitle();
		Set<PromotionVo> promotions = execute(title, () -> {
			int hotelId = getCurrentId();
			if (hotelId != Integer.MIN_VALUE)
				return handler.getHotelActivePromotion(hotelId);

			alertFail(title, "Not logged in yet");
			return null;
		});
		
		HotelPromotionManagerImpl.getInstance().setPromotion(promotions);
		HotelPromotionManagerImpl.getInstance().setHotelId(getCurrentId());
		return false;
	}
	
	private int getCurrentId() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? Integer.MIN_VALUE : curAcc.getId();
	}

}
