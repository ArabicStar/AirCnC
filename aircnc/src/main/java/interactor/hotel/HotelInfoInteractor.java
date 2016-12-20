package interactor.hotel;

public interface HotelInfoInteractor {
	public void getHotelInfo();

	public void getHotelAllPromotions();
	
	public void getHotelActivePromotions();
	
	public void getHotelAllOrders();

	public void getHotelOrdersByStatus();

	public void getHotelComments();

	public void getHotelRooms();

	public void updatePassword();

	public void updateBasicInfo();
	
	public void updateRooms();
}
