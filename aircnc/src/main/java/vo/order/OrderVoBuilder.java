package vo.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.promotion.Promotion;

public class OrderVoBuilder extends OrderInfoBuilder {
	public OrderVoBuilder() {
	}

	public OrderVoBuilder(OrderInfo info) {
		super(info);
	}

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setEntryTime(entryTime).setHasChildren(hasChildren).setLastTime(lastTime)
				.setOrderId(orderId).setPeopleNumber(peopleNumber).setRoomType(roomType).setStatus(status)
				.setStayDays(stayDays).setUserId(userId).setOriginalPrice(originalPrice).setHotelId(hotelId)
				.setHotelName(hotelName).setRoomNumber(roomNumber).setIsReviewed(isReviewed).setUserName(userName)
				.setPromotions(promotions).setDiscountPrice(discountPrice);
	}

	@Override
	public OrderVoBuilder setOrderId(String orderId) {
		super.setOrderId(orderId);
		return this;
	}

	@Override
	public OrderVoBuilder setRoomType(String roomType) {
		super.setRoomType(roomType);
		return this;
	}

	@Override
	public OrderVoBuilder setStayDays(int stayDays) {
		super.setStayDays(stayDays);
		return this;
	}

	@Override
	public OrderVoBuilder setUserId(int userId) {
		super.setUserId(userId);
		return this;
	}

	@Override
	public OrderVoBuilder setStatus(OrderStatus status) {
		super.setStatus(status);
		return this;
	}

	@Override
	public OrderVoBuilder setEntryTime(LocalDateTime entryTime) {
		super.setEntryTime(entryTime);
		return this;
	}

	@Override
	public OrderVoBuilder setLastTime(LocalDateTime lastTime) {
		super.setLastTime(lastTime);
		return this;
	}

	@Override
	public OrderVoBuilder setPeopleNumber(int peopleNumber) {
		super.setPeopleNumber(peopleNumber);
		return this;
	}

	@Override
	public OrderVoBuilder setOriginalPrice(double price) {
		super.setOriginalPrice(price);
		return this;
	}

	@Override
	public OrderVoBuilder setHasChildren(boolean hasChildren) {
		super.setHasChildren(hasChildren);
		return this;
	}

	@Override
	public OrderVoBuilder setHotelId(int hotelId) {
		super.setHotelId(hotelId);
		return this;
	}

	@Override
	public OrderVoBuilder setHotelName(String hotelName) {
		super.setHotelName(hotelName);
		return this;
	}

	@Override
	public OrderVoBuilder setRoomNumber(int roomNumber) {
		super.setRoomNumber(roomNumber);
		return this;
	}

	@Override
	public OrderVoBuilder setReviewed(boolean isReviewed) {
		super.setReviewed(isReviewed);
		return this;
	}

	@Override
	public OrderVoBuilder setUserName(String userName) {
		super.setUserName(userName);
		return this;
	}

	@Override
	public OrderVoBuilder setPromotions(Set<Promotion> promotions) {
		super.setPromotions(promotions);
		return this;
	}

	@Override
	public OrderVoBuilder setDiscountPrice(double discountPrice) {
		super.setDiscountPrice(discountPrice);
		return this;
	}
}
