package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.comment.CommentInfo;
import utils.info.promotion.PromotionInfo;

public abstract class OrderInfo extends OrderInfoTemplate {
	private boolean isValid = true;

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getLastTime() {
		return lastTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getStayDays() {
		return stayDays;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public boolean getHasChildren() {
		return hasChildren;
	}

	public boolean isReviewed() {
		return getComment() != null;
	}

	public String getAppeal() {
		return appeal;
	}

	public abstract MemberInfo getMember();

	public abstract HotelInfo getHotel();

	public abstract CommentInfo getComment();

	public abstract Set<? extends PromotionInfo> getPromotions();
}
