package po;

import java.util.Date;

public class PromotionPo {
	/**
	 * ��ʼ
	 * TODO�����������
	 */
	private Date startDate;
	/**
	 * ����
	 * TODO�����������
	 */
	private Date endDate;
	/**
	 * �ۿ�
	 */
	private double discount;
	/**
	 * �������
	 */
	private double minSpent;
	/**
	 * ���ؽ��
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
