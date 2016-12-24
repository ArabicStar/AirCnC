package interactor.hotel;

public interface HotelOrderInteractor {
	
	public void getHotelAllOrders();

	public void getHotelOrdersByStatus();
	
	public void executeOrder();
	
	public void appealOrder();

}
