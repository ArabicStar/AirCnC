package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;

import utils.promotion.Promotion;

@Entity
public abstract class OrderInfoTemplate {
	protected String orderId;

	protected String roomType;

	protected int stayDays;

	protected int userId;

	protected OrderStatus status;

	protected LocalDateTime entryTime;

	/**
	 * 订单最晚执行时间
	 */
	protected LocalDateTime lastTime;
	
	protected Set<Promotion> promotions;

	/**
	 * 除去小孩的总人数
	 */
	protected int peopleNumber;
	
	protected double originalPrice;
	
	protected double discountPrice;

	protected boolean hasChildren;
	
	protected int hotelId;
	
	protected String hotelName;
	
	protected int roomNumber;
	
	/**
	 * 是否评价过
	 */
	protected boolean isReviewed;
	
	protected String userName;
	
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

	/**
	 * 年份4位<br>
	 * 月份2位<br>
	 * 日期2位<br>
	 * 酒店id至少4位<br>
	 * 订单编号至少4位<br>
	 * 前八位固定为日期，后面2n位，取酒店id和订单编号位数较长的值，在较短的前面补0
	 * TODO:添加其他测试条件
	 * @return
	 */
	public static boolean checkOrderId(String orderId) {
		if(orderId.length() % 2 == 1) {
			return false;
		}
		if(orderId.length() < 16) {
			return false;
		}
		if(!isNumber(orderId)){
			return false;
		}
		return true;
	}
	
	private static boolean isNumber(String str) {
		if(str.matches("[0-9]+")) {
			return true;
		}
		return false;
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

	public boolean isHasChildren() {
		return hasChildren;
	}
	
	public boolean isIsReviewed() {
		return isReviewed;
	}

	public abstract OrderInfoTemplate setOrderId(String orderId);

	public abstract OrderInfoTemplate setRoomType(String roomType);

	public abstract OrderInfoTemplate setStayDays(int stayDays);

	public abstract OrderInfoTemplate setUserId(int userId);

	public abstract OrderInfoTemplate setStatus(OrderStatus status);

	public abstract OrderInfoTemplate setEntryTime(LocalDateTime entryTime);

	public abstract OrderInfoTemplate setLastTime(LocalDateTime lastTime);

	public abstract OrderInfoTemplate setPeopleNumber(int peopleNumber);

	public abstract OrderInfoTemplate setPrice(double price);

	public abstract OrderInfoTemplate setHasChildren(boolean hasChildren);
	
	public abstract OrderInfoTemplate setHotelId(int hotelId);
	
	public abstract OrderInfoTemplate setHotelName(String hotelName);
	
	public abstract OrderInfoTemplate setRoomNumber(int roomNumber);
	
	public abstract OrderInfoTemplate setIsReviewed(boolean isReviewed);

	public abstract OrderInfoTemplate setUserName(String userName);
	
	public abstract OrderInfoTemplate setPromotions(Set<Promotion> promotions);
	
	public abstract OrderInfoTemplate setDiscountPrice(double discountPrice);
}
