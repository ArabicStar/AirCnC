package po.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import po.hotel.HotelPo;
import po.member.MemberPo;
import po.order.comment.CommentPo;
import po.promotion.PromotionPo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import utils.info.promotion.PromotionInfoTemplate.Scope;

public class OrderPo extends OrderInfo {
	private Set<PromotionPo> promotions;
	private CommentPo comment;
	private MemberPo member;
	private HotelPo hotel;

	public OrderPo() {
		promotions = new HashSet<>();
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

	public OrderPo setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return this;
	}

	public OrderPo setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
		return this;
	}

	public OrderPo setAppeal(String appeal) {
		this.appeal = appeal;
		return this;
	}

	public OrderPo setPromotions(Set<PromotionPo> promotions) {
		this.promotions = new HashSet<>(promotions);
		return this;
	}

	@Override
	public Set<PromotionPo> getPromotions() {
		return new HashSet<>(promotions);
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

	public Set<PromotionPo> getHotelPromotions() {
		return promotions.stream().filter(po -> po.getScope() == Scope.Hotel).collect(Collectors.toSet());
	}

	public OrderPo setComments(CommentPo comment) {
		this.comment = comment;
		comment.setRelOrder(this);
		return this;
	}

	@Override
	public CommentPo getComments() {
		return comment;
	}

	public OrderPo setMember(MemberPo member) {
		this.member = member;
		return this;
	}

	@Override
	public MemberPo getMember() {
		return member;
	}

	public OrderPo setHotel(HotelPo hotel) {
		this.hotel = hotel;
		return this;
	}

	@Override
	public HotelPo getHotel() {
		return hotel;
	}
}
