package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.comment.CommentInfo;
import utils.info.promotion.PromotionInfo;

public abstract class OrderInfoBuilder extends OrderInfoTemplate {
	public OrderInfoBuilder() {
		orderId = BLANK;
		status = OrderStatus.UNEXECUTED;
		entryTime = null;
		lastTime = null;
		stayDays = -1;
		peopleNumber = -1;
		hasChildren = false;
		originalPrice = -1;
		discountPrice = -1;
		roomType = null;
		roomNumber = -1;
		appeal = null;
	}

	public OrderInfoBuilder(OrderInfo info) {
		this();
		this.setOrderId(info.getOrderId())//
				.setStatus(info.getStatus())//
				.setMember(info.getMember())//
				.setHotel(info.getHotel()).//
				setRoomType(info.getRoomType())//
				.setRoomNumber(info.getRoomNumber())//
				.setEntryTime(info.getEntryTime())//
				.setLastTime(info.getLastTime())//
				.setStayDays(info.getStayDays())//
				.setPeopleNumber(info.getPeopleNumber())//
				.setHasChildren(info.getHasChildren())//
				.setOriginalPrice(info.getOriginalPrice())//
				.setDiscountPrice(info.getDiscountPrice())//
				.setComment(info.getComment())//
				.setAppeal(info.getAppeal());//
	}

	/**
	 * @param orderId
	 *            要设置的 orderId
	 */
	public OrderInfoBuilder setOrderId(String orderId) {
		if (orderId == null)
			return this;

		if (checkOrderId(orderId))
			this.orderId = orderId;

		return this;
	}

	/**
	 * @param roomType
	 *            要设置的 roomType
	 */
	public OrderInfoBuilder setRoomType(String roomType) {
		if (roomType != null)
			this.roomType = roomType;

		return this;
	}

	/**
	 * @param stayDays
	 *            要设置的 stayDays
	 */
	public OrderInfoBuilder setStayDays(int stayDays) {
		if (stayDays > 0)
			this.stayDays = stayDays;

		return this;
	}

	/**
	 * @param status
	 *            要设置的 status
	 */
	public OrderInfoBuilder setStatus(OrderStatus status) {
		if (status != null)
			this.status = status;

		return this;
	}

	/**
	 * @param entryTime
	 *            要设置的 entryTime
	 */
	public OrderInfoBuilder setEntryTime(LocalDateTime entryTime) {
		if (entryTime != null)
			this.entryTime = entryTime;

		return this;
	}

	/**
	 * @param lastTime
	 *            要设置的 lastTime
	 */
	public OrderInfoBuilder setLastTime(LocalDateTime lastTime) {
		if (lastTime != null)
			this.lastTime = lastTime;

		return this;
	}

	/**
	 * @param peopleNumber
	 *            要设置的 peopleNumber
	 */
	public OrderInfoBuilder setPeopleNumber(int peopleNumber) {
		if (peopleNumber > 0)
			this.peopleNumber = peopleNumber;

		return this;
	}

	/**
	 * @param originalPrice
	 *            要设置的 originalPrice
	 */
	public OrderInfoBuilder setOriginalPrice(double originalPrice) {
		if (originalPrice >= 0)
			this.originalPrice = originalPrice;

		return this;
	}

	/**
	 * @param discountPrice
	 *            要设置的 discountPrice
	 */
	public OrderInfoBuilder setDiscountPrice(double discountPrice) {
		if (discountPrice >= 0)
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
	 * @param roomNumber
	 *            要设置的 roomNumber
	 */
	public OrderInfoBuilder setRoomNumber(int roomNumber) {
		if (roomNumber > 0)
			this.roomNumber = roomNumber;
		return this;
	}

	public OrderInfoBuilder setAppeal(String appeal) {
		if (appeal != null)
			this.appeal = appeal;
		return this;
	}

	public boolean isReady() {
		return checkOrderId(orderId) && getMember() != null && getHotel() != null && roomType != null && stayDays > 0
				&& status != null && entryTime != null && lastTime != null && peopleNumber > 0 && originalPrice >= 0
				&& discountPrice >= 0 && roomNumber > 0;
	}

	public abstract OrderInfoBuilder addPromotion(PromotionInfo promotion);

	/**
	 * @param promotions
	 *            要设置的 promotions
	 */
	public abstract OrderInfoBuilder setPromotions(Set<? extends PromotionInfo> promotions);

	public abstract OrderInfoBuilder setComment(CommentInfo comment);

	public abstract OrderInfoBuilder setHotel(HotelInfo info);

	public abstract OrderInfoBuilder setMember(MemberInfo info);

	protected abstract MemberInfo getMember();

	protected abstract HotelInfo getHotel();

	public abstract OrderInfo getOrderInfo();
}
