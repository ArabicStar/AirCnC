package po.order;

import utils.info.order.OrderInfoBuilder;

public class OrderPoBuilder extends OrderInfoBuilder {

	@Override
	public OrderPo getOrderInfo() {
		return new OrderPo().setEntryTime(entryTime).setHasChildren(hasChildren)
				.setLastTime(lastTime).setOrderId(orderId).setPeopleNumber(peopleNumber)
				.setPrice(price).setRoomType(roomType).setStatus(status).setStayDays(stayDays)
				.setUserId(userId);
	}

}
