package interactor.hotel;

public interface HotelInfoInteractor {
	public void getHotelInfo();

	public void getHotelComments();

	public void getHotelRooms();

	public void updatePassword();
	
	public void updateHotel();
	
	public void liveCheckIn();
	
	public void liveCheckOut();
	
	public void getHotelsByCondition();
}
