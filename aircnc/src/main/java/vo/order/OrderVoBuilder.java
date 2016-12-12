package vo.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.promotion.Promotion;

public class OrderVoBuilder extends OrderInfoBuilder {

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setEntryTime(entryTime).setHasChildren(hasChildren)
				.setLastTime(lastTime).setOrderId(orderId).setPeopleNumber(peopleNumber)
				.setRoomType(roomType).setStatus(status).setStayDays(stayDays)
				.setUserId(userId).setPrice(originalPrice).setHotelId(hotelId)
				.setHotelName(hotelName).setRoomNumber(roomNumber).setIsReviewed(isReviewed)
				.setUserName(userName).setPromotions(promotions);
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
		this.originalPrice = price;
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

	@Override
	public OrderVoBuilder setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	@Override
	public OrderVoBuilder setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderVoBuilder setIsReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	@Override
	public OrderVoBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	@Override
	public OrderVoBuilder setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
		return this;
	}
	
}
