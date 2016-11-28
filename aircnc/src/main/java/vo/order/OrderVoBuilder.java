package vo.order;

import java.time.LocalDateTime;

import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;

public class OrderVoBuilder extends OrderInfoBuilder {

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setEntryTime(entryTime).setHasChildren(hasChildren)
				.setLastTime(lastTime).setOrderId(orderId).setPeopleNumber(peopleNumber)
				.setRoomType(roomType).setStatus(status).setStayDays(stayDays)
				.setUserId(userId).setPrice(price).setHotelId(hotelId);
	}

	@Override
	public OrderVoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderVoBuilder setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderVoBuilder setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderVoBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public OrderVoBuilder setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderVoBuilder setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderVoBuilder setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderVoBuilder setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderVoBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public OrderVoBuilder setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	@Override
	public OrderVoBuilder setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}
	
}
