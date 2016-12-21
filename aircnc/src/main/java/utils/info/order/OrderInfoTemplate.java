package utils.info.order;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;

import po.order.comment.CommentPo;
import utils.info.promotion.PromotionInfo;

@Entity
public abstract class OrderInfoTemplate {
	protected static String BLANK = "";
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

	protected Set<? super PromotionInfo> promotions;

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
	
	protected CommentPo comments;
	
	/**
	 * 申诉内容
	 */
	protected String appeal;
	
	protected static boolean isNumber(String str) {
		if (str.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	/**
	 * 年份4位<br>
	 * 月份2位<br>
	 * 日期2位<br>
	 * 酒店id至少4位<br>
	 * 订单编号至少4位<br>
	 * 前八位固定为日期，后面2n位，取酒店id和订单编号位数较长的值，在较短的前面补0 TODO:添加其他测试条件
	 * 
	 * @return
	 */
	public static boolean checkOrderId(String orderId) {
		if (orderId.length() % 2 == 1) {
			return false;
		}
		if (orderId.length() < 16) {
			return false;
		}
		if (!isNumber(orderId)) {
			return false;
		}
		return true;
	}

}
