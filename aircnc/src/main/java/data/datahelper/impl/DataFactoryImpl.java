package data.datahelper.impl;

import data.datahelper.DataFactory;
import data.datahelper.HotelDataHelper;
import data.datahelper.MarketDataHelper;
import data.datahelper.OrderDataHelper;
import data.datahelper.PromotionDataHelper;
import data.datahelper.UserDataHelper;

public class DataFactoryImpl implements DataFactory{

	public OrderDataHelper getOrderDataHelper() {
		OrderDataHelper orderDao = new OrderDataTxtHelper();
		//OrderDao orderDao = new OrderDataMysqlHelper();
		return orderDao;
	}

	public UserDataHelper getUserDataHelper() {
		UserDataHelper userDao = new UserDataTxtHelper();
		//UserDao userDao = new UserDataMysqlHelper();
		return userDao;
	}

	public MarketDataHelper getMarketDataHelper() {
		MarketDataHelper marketDao = new MarketDataTxtHelper();
		//MarketDao marketDao = new MarketDataMysqlHelper();
		return marketDao;
	}

	@Override
	public HotelDataHelper getHotelDataHelper() {
		HotelDataHelper hotelDao = new HotelDataTxtHelper();
//		HotelDataHelper hotelDao = new HotelDataMysqlHelper();
		// TODO Auto-generated method stub
		return hotelDao;
	}

	@Override
	public PromotionDataHelper getPromotionDataHelper() {
		// TODO Auto-generated method stub
		PromotionDataHelper promotionDao = new PromotionDataTxtHelper();
		
		return promotionDao;
	}
	
	



}
