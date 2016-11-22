package data.datahelper.impl;

import data.datahelper.DataFactory;
import data.datahelper.HotelDataHelper;
import data.datahelper.MarketDataHelper;
import data.datahelper.OrderDataHelper;
import data.datahelper.PromotionDataHelper;
import data.datahelper.UserDataHelper;

public class DataFactoryImpl implements DataFactory{
	@Override
	public OrderDataHelper getOrderDataHelper() {
		OrderDataHelper orderDao = new OrderDataTxtHelper();
		//OrderDao orderDao = new OrderDataMysqlHelper();
		return orderDao;
	}

	public UserDataHelper getUserDataHelper() {
//		UserDataHelper userDao = new UserDataTxtHelper();
		//UserDao userDao = new UserDataMysqlHelper();
		// TODO:此处返回值为空，目标是在使用时报错
		return null;
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
		PromotionDataHelper promotionDao = new PromotionDataTxtHelper();
		
		return promotionDao;
	}

	@Override
	public MarketDataHelper getMarketDataHelper() {
		// TODO Auto-generated method stub
		return null;
	}
	
	



}
