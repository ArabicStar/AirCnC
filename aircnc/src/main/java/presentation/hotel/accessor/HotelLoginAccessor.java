package presentation.hotel.accessor;

public interface HotelLoginAccessor {
	
	public void setDeliveredId(String id);
	
	public void setDeliveredPassword(String password);
	
	public int getPasswordHash();
	
	public String getId();
	
}
