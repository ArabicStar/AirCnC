package data.dao.promotion;

import static data.dao.rmi.RmiHazarder.hazard;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.rmi.promotion.RemoteHotelPromotionDao;
import data.dao.rmi.promotion.RemoteWebsitePromotionDao;
import po.promotion.HotelPromotionPo;
import po.promotion.WebsitePromotionPo;

public final class PromotionDaoProxy implements HotelPromotionDao, WebsitePromotionDao {
	private static PromotionDaoProxy instance;

	public static PromotionDaoProxy launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new PromotionDaoProxy();
	}

	public static PromotionDaoProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private PromotionDaoProxy() {
		super();
	}

	/*
	 ************************************
	 ******* HotelPromotionDao*******
	 ************************************
	 */
	private RemoteWebsitePromotionDao remoteWebsiteDao;

	public void loadRemoteWebsiteDao(RemoteWebsitePromotionDao remoteWebsiteDao) {
		this.remoteWebsiteDao = remoteWebsiteDao;
	}

	@Override
	public boolean addWebsitePromotion(WebsitePromotionPo po) {
		return hazard(() -> {
			return remoteWebsiteDao.addWebsitePromotion(po);
		});
	}

	@Override
	public boolean deleteWebsitePromotion(long id) {
		return hazard(() -> {
			return remoteWebsiteDao.deleteWebsitePromotion(id);
		});
	}

	@Override
	public boolean updateWebsitePromotion(WebsitePromotionPo po) {
		return hazard(() -> {
			return remoteWebsiteDao.updateWebsitePromotion(po);
		});
	}

	/*
	 **************************************
	 ******* WebsitePromotionDao*******
	 **************************************
	 */
	@Override
	public WebsitePromotionPo findWebsitePromotion(long id) {
		return hazard(() -> {
			return remoteWebsiteDao.findWebsitePromotion(id);
		});
	}

	private RemoteHotelPromotionDao remoteHotelDao;

	public void loadRemoteHotelDao(RemoteHotelPromotionDao remoteHotelDao) {
		this.remoteHotelDao = remoteHotelDao;
	}

	@Override
	public boolean addHotelPromotion(HotelPromotionPo po) {
		return hazard(() -> {
			return remoteHotelDao.addHotelPromotion(po);
		});
	}

	@Override
	public boolean deleteHotelPromotion(long id) {
		return hazard(() -> {
			return remoteHotelDao.deleteHotelPromotion(id);
		});
	}

	@Override
	public boolean updateHotelPromotion(HotelPromotionPo po) {
		return hazard(() -> {
			return remoteHotelDao.updateHotelPromotion(po);
		});
	}

	@Override
	public HotelPromotionPo findHotelPromotion(long id) {
		return hazard(() -> {
			return remoteHotelDao.findHotelPromotion(id);
		});
	}

}
