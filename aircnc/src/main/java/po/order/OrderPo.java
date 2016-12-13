package po.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import utils.promotion.Promotion;

public class OrderPo extends OrderInfo {

	public OrderPo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	public OrderPo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public OrderPo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	public OrderPo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	public OrderPo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public OrderPo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	public OrderPo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	public OrderPo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	public OrderPo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	public OrderPo setPrice(double price) {
		this.originalPrice = price;
		return this;
	}

	public OrderPo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	public OrderPo setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	public OrderPo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	public OrderPo setIsReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	public OrderPo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public OrderPo setPromotions(Set<Promotion<?>> promotions) {
		this.promotions = promotions;
		return this;
	}

	public OrderPo setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

}
