package data.datahelper.impl;

import data.dao.OrderDao;
import data.dao.UserDao;
import data.dao.MarketDao;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import data.dao.impl.MarketDaoImpl;
import data.datahelper.DataFactory;
import data.datahelper.OrderDataHelper;
import data.datahelper.UserDataHelper;
import data.datahelper.MarketDataHelper;

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



}
