package po.order;

import static utils.exception.StaticExceptionFactory.inconsistentStatusEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import po.promotion.PromotionPoBuilder;
import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.info.promotion.PromotionInfo;

public class OrderPoBuilder extends OrderInfoBuilder {
	private Set<PromotionPo> promotions;

	public OrderPoBuilder() {
		super();
		promotions = new HashSet<>();
	}

	public OrderPoBuilder(OrderInfo info) {
		super(info);
		promotions = new HashSet<>();
	}

	@Override
	public OrderPo getOrderInfo() {
		return new OrderPo().setOrderId(orderId).setEntryTime(entryTime).setHasChildren(hasChildren).setHotelId(hotelId)
				.setHotelName(hotelName).setLastTime(lastTime).setPeopleNumber(peopleNumber)
				.setOriginalPrice(originalPrice).setReviewed(isReviewed).setRoomNumber(roomNumber).setRoomType(roomType)
				.setStatus(status).setStayDays(stayDays).setUserId(userId).setUserName(userName)
				.setPromotionsInfo(promotions).setDiscountPrice(discountPrice).setComments(comments).setAppeal(appeal);
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

		if (!from.getReviewed() && to.getReviewed()) {
			throw unsupportedOpEx("Couldn't make the reviewed order unreviewed");
		}

		to.setStatus(from.getStatus());
		to.setLastTime(from.getLastTime());
		to.setReviewed(from.getReviewed());
	}

	@Override
	public OrderPoBuilder setPromotions(Set<? extends PromotionInfo> promotions) {
		this.promotions.addAll(promotions.stream().map(info -> new PromotionPoBuilder(info).getPromotionInfo())
				.collect(Collectors.toSet()));
		return this;
	}

	@Override
	public OrderPoBuilder setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	@Override
	public OrderPoBuilder setComments(CommentPo comments) {
		this.comments = comments;
		return this;
	}

	@Override
	public OrderInfoBuilder addPromotion(PromotionInfo promotion) {
		promotions.add(new PromotionPoBuilder(promotion).getPromotionInfo());
		return this;
	}
}
