package utils.info.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import po.order.comment.CommentPo;
import utils.promotion.Promotion;

public abstract class OrderInfoBuilder extends OrderInfoTemplate {
	public OrderInfoBuilder() {
		orderId = BLANK;
		stayDays = -1;
		userId = -1;
		userName = BLANK;
		status = OrderStatus.ABNORMAL;
		entryTime = null;
		lastTime = null;
		promotions = new HashSet<>();
		peopleNumber = -1;
		originalPrice = -1;
		discountPrice = -1;
		hasChildren = false;
		hotelId = -1;
		hotelName = BLANK;
		roomNumber = -1;
		isReviewed = false;
	}

	public OrderInfoBuilder(OrderInfo info) {
		this.setOrderId(orderId).setRoomType(info.getRoomType()).setStayDays(info.getStayDays())
				.setUserId(info.getUserId()).setStatus(info.getStatus()).setEntryTime(info.getEntryTime())
				.setLastTime(info.getLastTime()).setPromotions(info.getPromotions())
				.setPeopleNumber(info.getPeopleNumber()).setOriginalPrice(info.getOriginalPrice())
				.setDiscountPrice(info.getDiscountPrice()).setHasChildren(info.getHasChildren())
				.setHotelId(info.getHotelId()).setRoomNumber(info.getRoomNumber()).setReviewed(info.getReviewed());
	}

	/**
	 * @param orderId
	 *            要设置的 orderId
	 */
	public OrderInfoBuilder setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	/**
	 * @param roomType
	 *            要设置的 roomType
	 */
	public OrderInfoBuilder setRoomType(String roomType) {
		this.roomType = roomType;
		return this;
	}

	/**
	 * @param stayDays
	 *            要设置的 stayDays
	 */
	public OrderInfoBuilder setStayDays(int stayDays) {
		this.stayDays = stayDays;
		return this;
	}

	/**
	 * @param userId
	 *            要设置的 userId
	 */
	public OrderInfoBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * @param status
	 *            要设置的 status
	 */
	public OrderInfoBuilder setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * @param entryTime
	 *            要设置的 entryTime
	 */
	public OrderInfoBuilder setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
		return this;
	}

	/**
	 * @param lastTime
	 *            要设置的 lastTime
	 */
	public OrderInfoBuilder setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	/**
	 * @param promotions
	 *            要设置的 promotions
	 */
	public OrderInfoBuilder setPromotions(Set<Promotion> promotions) {
		if(promotions == null) {
			System.err.println("Promotion is null");
			return this;
		}
		this.promotions.addAll(promotions);
		return this;
	}

	public OrderInfoBuilder addPromotion(Promotion promotion) {
		this.promotions.add(promotion);
		return this;
	}

	/**
	 * @param peopleNumber
	 *            要设置的 peopleNumber
	 */
	public OrderInfoBuilder setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
		return this;
	}

	/**
	 * @param originalPrice
	 *            要设置的 originalPrice
	 */
	public OrderInfoBuilder setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
		return this;
	}

	/**
	 * @param discountPrice
	 *            要设置的 discountPrice
	 */
	public OrderInfoBuilder setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	/**
	 * @param hasChildren
	 *            要设置的 hasChildren
	 */
	public OrderInfoBuilder setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		return this;
	}

	/**
	 * @param hotelId
	 *            要设置的 hotelId
	 */
	public OrderInfoBuilder setHotelId(int hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	/**
	 * @param hotelName
	 *            要设置的 hotelName
	 */
	public OrderInfoBuilder setHotelName(String hotelName) {
		this.hotelName = hotelName;
		return this;
	}

	/**
	 * @param roomNumber
	 *            要设置的 roomNumber
	 */
	public OrderInfoBuilder setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	/**
	 * @param isReviewed
	 *            要设置的 isReviewed
	 */
	public OrderInfoBuilder setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public OrderInfoBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public OrderInfoBuilder setComments(Set<CommentPo> comments) {
		this.comments = comments;
		return this;
	}

	public abstract OrderInfo getOrderInfo();
}
