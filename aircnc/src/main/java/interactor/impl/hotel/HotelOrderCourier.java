package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import java.util.List;
import java.util.stream.Collectors;
import interactor.hotel.HotelOrderInteractor;
import interactor.utils.Title;
import presentation.hotel.accessor.impl.InfoModifyAccessorImpl;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelOrderService;
import utils.info.hotel.HotelInfo;
import vo.order.OrderVo;

public class HotelOrderCourier implements HotelOrderInteractor {

	private static HotelOrderInteractor instance;

	public static HotelOrderInteractor launch(HotelOrderService handler, HotelAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelOrderCourier(handler, helper);
	}

	public static HotelOrderInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelOrderService handler;
	private HotelAccountService helper;

	private HotelOrderCourier(HotelOrderService handler, HotelAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}
	
	@Override
	@Title("Get Hotel Comments")
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

	@Override
	@Title("Get Orders")
	public void getHotelOrdersByStatus() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			int id = getCurrentId();

			if (id == Integer.MIN_VALUE) {
				alertFail(title, "Not logged in yet");
				return null;
			}

			return SearchOrderAccessorImpl.getInstance().getStatus().stream()
					.map(status -> handler.getHotelOrdersByStatus(id, status))
					.collect(Collectors.reducing((l1, l2) -> {
						l1.addAll(l2);
						return l1;
					})).get();
		});

		HotelOrderManagerImpl.getInstance().setOrderList(list);
		
	}
	
	private int getCurrentId() {
		HotelInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? Integer.MIN_VALUE : curAcc.getId();
	}

	@Override
	@Title("订单执行")
	public void executeOrder() {
		String title = getTitle();

		execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)

				if (!handler.executeOrder(SearchOrderAccessorImpl.getInstance().getOrderVo()))
					alertFail(title, "执行失败");
				else
					alertSuccess(title, "订单执行成功");
			return null;
		});
		
		
		
	}

	@Override
	@Title("延迟入住")
	public void appealOrder() {
		// TODO Auto-generated method stub
	}
}
