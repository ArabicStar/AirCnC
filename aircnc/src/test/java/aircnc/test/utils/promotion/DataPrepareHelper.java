package aircnc.test.utils.promotion;

import java.time.LocalDateTime;

import utils.info.order.OrderInfo;
import vo.order.OrderVoBuilder;

public class DataPrepareHelper {

	public static final OrderInfo getAnOrder() {
		return new OrderVoBuilder().setEntryTime(LocalDateTime.now().plusDays(2)).setOriginalPrice(100).setRoomNumber(3)
				.getOrderInfo();
	}
}
