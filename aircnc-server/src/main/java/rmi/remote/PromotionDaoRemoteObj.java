package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.impl.promotion.HotelPromotionDaoImpl;
import data.dao.impl.promotion.WebsitePromotionDaoImpl;
import data.dao.promotion.HotelPromotionDao;
import data.dao.promotion.WebsitePromotionDao;
import data.dao.rmi.promotion.RemoteHotelPromotionDao;
import data.dao.rmi.promotion.RemoteWebsitePromotionDao;
import po.promotion.HotelPromotionPo;
import po.promotion.WebsitePromotionPo;
import rmi.RemoteHelper;

/*final*/
public class PromotionDaoRemoteObj extends UnicastRemoteObject
		implements RemoteHotelPromotionDao, RemoteWebsitePromotionDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4898254082326653645L;

	/* Singleton */
	private static PromotionDaoRemoteObj instance;

	public static void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final HotelPromotionDao hotelPromotionDao = HotelPromotionDaoImpl.INSTANCE;
		final WebsitePromotionDao websitePromotionDao = WebsitePromotionDaoImpl.INSTANCE;

		instance = new PromotionDaoRemoteObj(hotelPromotionDao, websitePromotionDao);

		RemoteHelper.bindRemoteObj("RemotePromotionDao", instance);
	}
	/* Singleton */

	private PromotionDaoRemoteObj(HotelPromotionDao hotelPromotionDao, WebsitePromotionDao websitePromotionDao)
			throws RemoteException {
		super();
		this.hotelPromotionDao = hotelPromotionDao;
		this.websitePromotionDao = websitePromotionDao;
	}

	/*
	 **************************************
	 ******* WebsitePromotionDao*******
	 **************************************
	 */
	private WebsitePromotionDao websitePromotionDao;

	@Override
	public boolean addWebsitePromotion(WebsitePromotionPo po) throws RemoteException {
		return websitePromotionDao.addWebsitePromotion(po);
	}

	@Override
	public boolean deleteWebsitePromotion(long id) throws RemoteException {
		return websitePromotionDao.deleteWebsitePromotion(id);
	}

	@Override
	public boolean updateWebsitePromotion(WebsitePromotionPo po) throws RemoteException {
		return websitePromotionDao.updateWebsitePromotion(po);
	}

	@Override
	public WebsitePromotionPo findWebsitePromotion(long id) throws RemoteException {
		return websitePromotionDao.findWebsitePromotion(id);
	}

	/*
	 ************************************
	 ******* HotelPromotionDao*******
	 ************************************
	 */
	private HotelPromotionDao hotelPromotionDao;

	@Override
	public boolean addHotelPromotion(HotelPromotionPo po) throws RemoteException {
		return hotelPromotionDao.addHotelPromotion(po);
	}

	@Override
	public boolean deleteHotelPromotion(long id) throws RemoteException {
		return hotelPromotionDao.deleteHotelPromotion(id);
	}

	@Override
	public boolean updateHotelPromotion(HotelPromotionPo po) throws RemoteException {
		return hotelPromotionDao.updateHotelPromotion(po);
	}

	@Override
	public HotelPromotionPo findHotelPromotion(long id) throws RemoteException {
		return hotelPromotionDao.findHotelPromotion(id);
	}

}
