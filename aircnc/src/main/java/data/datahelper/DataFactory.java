package data.datahelper;

public interface DataFactory {

	public OrderDataHelper getOrderDataHelper();

	public UserDataHelper getUserDataHelper();

	public MarketDataHelper getMarketDataHelper();
	
	public HotelDataHelper getHotelDataHelper();

}
