package po;

import utils.HotelDate;
import vo.OrderVo;

public class PromotionPo {
	/**
	 * 开始
	 */
	private HotelDate startDate;
	/**
	 * 结束
	 */
	private HotelDate endDate;
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
	
	
	public HotelDate getStartDate() {
		return startDate;
	}
	public void setStartDate(HotelDate startDate) {
		this.startDate = startDate;
	}
	public HotelDate getEndDate() {
		return endDate;
	}
	public void setEndDate(HotelDate endDate) {
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
	
	
	public OrderPo apply(OrderVo vo) {
		return new OrderPo();
	}
	

}
