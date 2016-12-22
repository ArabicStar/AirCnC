package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import po.order.comment.CommentPo;
import utils.info.promotion.PromotionInfo;

public abstract class OrderInfoBuilder extends OrderInfoTemplate {
	public OrderInfoBuilder() {
		orderId = BLANK;
		stayDays = -1;
		userId = -1;
		userName = BLANK;
		status = OrderStatus.UNEXECUTED;
		entryTime = null;
		lastTime = null;
		peopleNumber = -1;
		originalPrice = -1;
		discountPrice = -1;
		hasChildren = false;
		hotelId = -1;
		hotelName = BLANK;
		roomNumber = -1;
		isReviewed = false;
		appeal = BLANK;
		comments = null;
	}

	public OrderInfoBuilder(OrderInfo info) {
		this();
		this.setOrderId(orderId).setRoomType(info.getRoomType()).setStayDays(info.getStayDays())
				.setUserId(info.getUserId()).setStatus(info.getStatus()).setEntryTime(info.getEntryTime())
				.setLastTime(info.getLastTime()).setPeopleNumber(info.getPeopleNumber())
				.setOriginalPrice(info.getOriginalPrice()).setDiscountPrice(info.getDiscountPrice())
				.setHasChildren(info.getHasChildren()).setHotelId(info.getHotelId()).setRoomNumber(info.getRoomNumber())
				.setReviewed(info.getReviewed()).setAppeal(info.getAppeal()).setComments(info.comments);
	}

	/**
	 * @param orderId
	 *            要设置的 orderId
	 */
	public OrderInfoBuilder setOrderId(String orderId) {
		if(orderId == null) {
			return null;
		}
		if(checkOrderId(orderId)){
			this.orderId = orderId;
		}
		return this;
	}

	/**
	 * @param roomType
	 *            要设置的 roomType
	 */
	public OrderInfoBuilder setRoomType(String roomType) {
		if(roomType == null) {
			return null;
		}
		this.roomType = roomType;
		return this;
	}

	/**
	 * @param stayDays
	 *            要设置的 stayDays
	 */
	public OrderInfoBuilder setStayDays(int stayDays) {
		if(stayDays <= 0) {
			return null;
		}
		this.stayDays = stayDays;
		return this;
	}

	/**
	 * @param userId
	 *            要设置的 userId
	 */
	public OrderInfoBuilder setUserId(int userId) {
		if(userId <= 0) {
			return null;
		}
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
	public abstract OrderInfoBuilder setPromotions(Set<? extends PromotionInfo> promotions);

	public abstract OrderInfoBuilder addPromotion(PromotionInfo promotion);

	/**
	 * @param peopleNumber
	 *            要设置的 peopleNumber
	 */
	public OrderInfoBuilder setPeopleNumber(int peopleNumber) {
		if(peopleNumber <= 0) {
			return null;
		}
		this.peopleNumber = peopleNumber;
		return this;
	}

	/**
	 * @param originalPrice
	 *            要设置的 originalPrice
	 */
	public OrderInfoBuilder setOriginalPrice(double originalPrice) {
		if(originalPrice <= 0) {
			return null;
		}
		this.originalPrice = originalPrice;
		return this;
	}

	/**
	 * @param discountPrice
	 *            要设置的 discountPrice
	 */
	public OrderInfoBuilder setDiscountPrice(double discountPrice) {
		if(discountPrice <= 0) {
			return null;
		}
		if(discountPrice > originalPrice) {
			return null;
		}
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
		if(hotelId <= 0) {
			return null;
		}
		this.hotelId = hotelId;
		return this;
	}

	/**
	 * @param hotelName
	 *            要设置的 hotelName
	 */
	public OrderInfoBuilder setHotelName(String hotelName) {
		if(hotelName == null) {
			return null;
		}
		this.hotelName = hotelName;
		return this;
	}

	/**
	 * @param roomNumber
	 *            要设置的 roomNumber
	 */
	public OrderInfoBuilder setRoomNumber(int roomNumber) {
		if(roomNumber <= 0) {
			return null;
		}
		this.roomNumber = roomNumber;
		return this;
	}

	/**
	 * @param isReviewed
	 *            要设置的 isReviewed
	 */
	public OrderInfoBuilder setReviewed(boolean isReviewed) {
		/**
		 * The order that has been reviewed can't be cast to a unreviewed one
		 */
		if(this.isReviewed == true) {
			return this;
		}
		this.isReviewed = isReviewed;
		return this;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public OrderInfoBuilder setUserName(String userName) {
		if(userName == null) {
			return null;
		}
		this.userName = userName;
		return this;
	}
	

	public OrderInfoBuilder setComments(CommentPo comments) {
		if(comments == null) {
			return null;
		}
		this.comments = comments;
		return this;
	}

	/**
	 * 申诉内容
	 * @param appeal
	 * @return
	 */
	public OrderInfoBuilder setAppeal(String appeal) {
		if(appeal == null) {
			return null;
		}
		this.appeal = appeal;
		return this;
	}

	public abstract OrderInfo getOrderInfo();
}
