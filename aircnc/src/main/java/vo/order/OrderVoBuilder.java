package vo.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.order.comment.CommentPo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderInfoBuilder;
import utils.info.order.OrderStatus;
import utils.info.promotion.PromotionInfo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class OrderVoBuilder extends OrderInfoBuilder {
	private Set<PromotionVo> promotions;

	public OrderVoBuilder() {
		super();
		promotions = new HashSet<>();
	}

	public OrderVoBuilder(OrderInfo info) {
		super(info);
		promotions = new HashSet<>();
	}

	@Override
	public OrderVo getOrderInfo() {
		return new OrderVo().setEntryTime(entryTime).setHasChildren(hasChildren).setLastTime(lastTime)
				.setOrderId(orderId).setPeopleNumber(peopleNumber).setRoomType(roomType).setStatus(status)
				.setStayDays(stayDays).setUserId(userId).setOriginalPrice(originalPrice).setHotelId(hotelId)
				.setHotelName(hotelName).setRoomNumber(roomNumber).setIsReviewed(isReviewed).setUserName(userName)
				.setPromotions(promotions).setDiscountPrice(discountPrice).setComments(comments);
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
	public OrderVoBuilder setDiscountPrice(double discountPrice) {
		super.setDiscountPrice(discountPrice);
		return this;
	}

	@Override
	public OrderVoBuilder setComments(CommentPo comments) {
		this.comments = comments;
		return this;
	}

	@Override
	public OrderVoBuilder setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	@Override
	public OrderVoBuilder setPromotions(Set<? extends PromotionInfo> promotions) {
		this.promotions.addAll(promotions.stream().map(info -> new PromotionVoBuilder(info).getPromotionInfo())
				.collect(Collectors.toSet()));
		return this;
	}

	@Override
	public OrderVoBuilder addPromotion(PromotionInfo promotion) {
		this.promotions.add(new PromotionVoBuilder(promotion).getPromotionInfo());
		return this;
	}
}
