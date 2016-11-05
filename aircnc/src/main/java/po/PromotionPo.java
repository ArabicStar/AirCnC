package po;

import java.util.Date;

public class PromotionPo {
	/**
	 * 开始
	 * TODO：换掉这个类
	 */
	private Date startDate;
	/**
	 * 结束
	 * TODO：换掉这个类
	 */
	private Date endDate;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
