package po.order;

import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.promotion.Promotion;

public class OrderPoBuilder extends OrderInfoBuilder {

	@Override
	public OrderPo getOrderInfo() {
		return new OrderPo().setOrderId(orderId).setEntryTime(entryTime).setHasChildren(hasChildren).setHotelId(hotelId)
				.setHotelName(hotelName).setLastTime(lastTime).setPeopleNumber(peopleNumber).setPrice(originalPrice)
				.setIsReviewed(isReviewed).setRoomNumber(roomNumber).setRoomType(roomType).setStatus(status)
				.setStayDays(stayDays).setUserId(userId).setUserName(userName).setPromotions(promotions)
				.setDiscountPrice(discountPrice);
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
	public OrderPoBuilder setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
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

	@Override
	public OrderPoBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	@Override
	public OrderPoBuilder setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	/**
	 * 
	 * @param from
	 *            the po you want
	 * @param to
	 *            the po you have
	 */
	public static void updatePo(OrderPo from, OrderPo to) {
		if (from == null || to == null || from == to) {
			return;
		}

		if (!from.getOrderId().equals(to.getOrderId())) {
			throw inconsistentStatusEx();
		}

		if (from.getLastTime().isBefore(to.getLastTime())) {
			throw unsupportedOpEx("Couldn't advance the last entry time");
		}

		if (!from.isIsReviewed() && to.isIsReviewed()) {
			throw unsupportedOpEx("Couldn't make the reviewed order unreviewed");
		}

		to.setStatus(from.getStatus());
		to.setLastTime(from.getLastTime());
		to.setIsReviewed(from.isIsReviewed());
	}

	@Override
	public OrderPoBuilder setPromotions(Set<Promotion<?>> promotions) {
		this.promotions = promotions;
		return this;
	}

}
