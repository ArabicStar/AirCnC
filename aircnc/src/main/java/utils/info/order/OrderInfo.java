package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import po.order.comment.CommentPo;
import utils.promotion.Promotion;

public abstract class OrderInfo extends OrderInfoTemplate {
	public String getUserName() {
		return userName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getHotelId() {
		return hotelId;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
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

	public int getUserId() {
		return userId;
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

	public boolean getReviewed() {
		return isReviewed;
	}
	
	public CommentPo getComments() {
		return comments;
	}
	
	public String getAppeal() {
		return appeal;
	}
}
