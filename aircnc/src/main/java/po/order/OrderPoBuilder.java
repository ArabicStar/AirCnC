package po.order;

import java.time.LocalDateTime;

import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;

public class OrderPoBuilder extends OrderInfoBuilder {

	@Override
	public OrderPo getOrderInfo() {
		// TODO 生成非空的OrderPo
		return null;
	}

	@Override
	public OrderPoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderPoBuilder setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderPoBuilder setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderPoBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public OrderPoBuilder setStatus(OrderStatus status) {

		this.status = status;
		return this;
	}

	@Override
	public OrderPoBuilder setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderPoBuilder setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderPoBuilder setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderPoBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public OrderPoBuilder setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	@Override
	public OrderPoBuilder setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	@Override
	public OrderPoBuilder setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	@Override
	public OrderPoBuilder setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderPoBuilder setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

}
