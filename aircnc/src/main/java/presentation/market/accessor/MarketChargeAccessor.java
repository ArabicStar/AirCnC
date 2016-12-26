package presentation.market.accessor;

/**
 * 营销人员
 * 信用充值
 * @author Water
 *
 */
public interface MarketChargeAccessor {
	public String getMemberId();
	
	public int getTopupMoney();
	
	public void setMemberId(String memberId);
	
	public void setTopupMoney(int money);
}
