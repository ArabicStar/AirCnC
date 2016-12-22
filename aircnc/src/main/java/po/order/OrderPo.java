package po.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import utils.info.promotion.PromotionInfoTemplate.Scope;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class OrderPo extends OrderInfo {
	private Set<PromotionPo> promotions;

	public OrderPo() {
		promotions = new HashSet<>();
	}

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

	public OrderPo setOriginalPrice(double price) {
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

	public OrderPo setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
		return this;
	}

	public OrderPo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public OrderPo setPromotionsInfo(Set<PromotionPo> promotions) {
		this.promotions = new HashSet<>(promotions);
		return this;
	}

	public OrderPo setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	public OrderPo setComments(CommentPo comments) {
		this.comments = comments;
		return this;
	}

	public OrderPo setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	public OrderVo orderPo2Vo() {
		return new OrderVoBuilder(this).getOrderInfo();
	}

	@Override
	public Set<PromotionPo> getPromotions() {
		return new HashSet<>(promotions);
	}

	public Set<PromotionPo> getHotelPromotions() {
		return promotions.stream().filter(po -> po.getScope() == Scope.Hotel).collect(Collectors.toSet());
	}

	public void setWebsitePromotions(Set<PromotionPo> promotions) {
		this.promotions.addAll(promotions);
	}

	public Set<PromotionPo> getWebsitePromotions() {
		return promotions.stream().filter(po -> po.getScope() == Scope.Website).collect(Collectors.toSet());
	}

	public void setHotelPromotions(Set<PromotionPo> promotions) {
		this.promotions.addAll(promotions);
	}
}
