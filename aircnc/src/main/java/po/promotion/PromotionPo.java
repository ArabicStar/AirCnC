package po.promotion;

import java.time.LocalDateTime;

public class PromotionPo {
	/**
	 * 开始
	 */
	private LocalDateTime startDate;
	/**
	 * 结束
	 */
	private LocalDateTime endDate;
	/**
	 * 折扣
	 */
	private double discount;
	/**
	 * 最低消费
	 */
	private double minSpent;
	/**
	 * 返回金额
	 */
	private double returnMoney;
	
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getMinSpent() {
		return minSpent;
	}
	public void setMinSpent(double minSpent) {
		this.minSpent = minSpent;
	}
	public double getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(double returnMoney) {
		this.returnMoney = returnMoney;
	}
	
}
