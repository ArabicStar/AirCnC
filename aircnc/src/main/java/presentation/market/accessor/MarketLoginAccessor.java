package presentation.market.accessor;

public interface MarketLoginAccessor {
	public String getId();
	
	public int getPasswordHash();
	
	public void setDeliveredId(String id);
	
	public void setDeliveredPassword(String password);
}
