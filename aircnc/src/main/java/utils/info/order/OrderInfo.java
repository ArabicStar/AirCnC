package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.member.MemberInfo;
import utils.info.order.comment.CommentInfo;
import utils.info.promotion.PromotionInfo;

public abstract class OrderInfo extends OrderInfoTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1666126585583703603L;
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
		return getComments() != null;
	}

	public String getAppeal() {
		return appeal;
	}

	public abstract MemberInfo getMember();

	public abstract HotelInfo getHotel();

	public abstract CommentInfo getComments();

	public abstract Set<? extends PromotionInfo> getPromotions();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderInfo [orderId=").append(orderId).append(", status=").append(status).append(", entryTime=")
				.append(entryTime).append(", lastTime=").append(lastTime).append(", stayDays=").append(stayDays)
				.append(", roomNumber=").append(roomNumber).append(", roomType=").append(roomType)
				.append(", peopleNumber=").append(peopleNumber).append(", hasChildren=").append(hasChildren)
				.append(", originalPrice=").append(originalPrice).append(", discountPrice=").append(discountPrice)
				.append(", appeal=").append(appeal).append(", getMember()=").append(getMember()).append(", getHotel()=")
				.append(getHotel()).append(", getComment()=").append(getComments()).append("]");
		return builder.toString();
	}

}
