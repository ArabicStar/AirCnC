package presentation.hotel.accessor;

public interface HotelLoginAccessor {
	
	public void setDeliveredName(String name);
	
	public void setDeliveredPassword(String password);
	
	public int getPasswordHash();
	
	public String getName();
	
}
