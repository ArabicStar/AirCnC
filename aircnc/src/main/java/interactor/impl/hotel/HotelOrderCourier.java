package interactor.impl.hotel;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;

import java.util.List;
import java.util.stream.Collectors;

import interactor.hotel.HotelOrderInteractor;
import presentation.hotel.accessor.impl.SearchOrderAccessorImpl;
import presentation.hotel.manager.impl.HotelOrderManagerImpl;
import service.hotel.HotelAccountService;
import service.hotel.HotelOrderService;
import utils.info.hotel.HotelInfo;
import vo.order.OrderVo;

public class HotelOrderCourier implements HotelOrderInteractor {

	private HotelOrderService orderService;
	private HotelAccountService helper;

	@Override
	public void getHotelAllOrders() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id != Integer.MIN_VALUE)
				return orderService.getHotelAllOrders(id);

			alertFail(title, "Not logged in yet");
			return null;
		});

		HotelOrderManagerImpl.getInstance().setOrderList(list);

	}

	@Override
	public void getHotelOrdersByStatus() {
		String title = getTitle();

		List<OrderVo> list = execute(title, () -> {
			int id = getCurrentId();
			if (id == Integer.MIN_VALUE) {
				alertFail(title, "Not logged in yet");
				return null;
			}

			return SearchOrderAccessorImpl.getInstance().getStatus().stream()
					.map(status -> orderService.getHotelOrdersByStatus(id, status))
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
}
