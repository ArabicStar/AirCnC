package po.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import utils.promotion.Promotion;

public class OrderPo extends OrderInfo{



	public OrderPo() {
		super();
	}

	public int getHotelId() {
		return hotelId;
	}

	public OrderPo setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	@Override
	public OrderPo setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	@Override
	public OrderPo setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	@Override
	public OrderPo setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	@Override
	public OrderPo setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public OrderPo setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderPo setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	@Override
	public OrderPo setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public OrderPo setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	@Override
	public OrderPo setPrice(double price) {
		this.originalPrice = price;
		return this;
	}

	@Override
	public OrderPo setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}
	
	@Override
	public OrderPo setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	@Override
	public OrderPo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	@Override
	public OrderPo setIsReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	@Override
	public OrderPo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	@Override
	public OrderPo setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
		return this;
	}

	@Override
	public OrderPo setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}
	

}
