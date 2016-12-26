package rmi;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import data.dao.rmi.hotel.RemoteHotelDao;
import data.dao.rmi.member.RemoteCreditDao;
import data.dao.rmi.member.RemoteMemberDao;
import data.dao.rmi.promotion.RemoteHotelPromotionDao;
import data.dao.rmi.promotion.RemoteWebsitePromotionDao;
import data.dao.rmi.query.RemoteCreditQueryDao;
import data.dao.rmi.query.RemotePromotionQueryDao;
import utils.logger.Log;

public class RemoteHelper {
	private static RemoteHelper instance;

	public static final void launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		Log.d("remote helper start to work at port:" + PORT);
		instance = new RemoteHelper();
		instance.init();
	}

	public static final RemoteHelper getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private RemoteHelper() {
	}

	private void init() {
		fetchHotelDaoRemoteObj();
		fetchQueryDaoRemoteObj();
		fetchPromotionDaoRemoteObj();
	}

	private void fetchQueryDaoRemoteObj() {
		queryDaoRemoteObj = findRemote("RemoteQueryDao");

		Log.d("fetch query dao remote obj " + (queryDaoRemoteObj != null ? "succeed" : "failed"));
	}

	private void fetchHotelDaoRemoteObj() {
		hotelDaoRemoteObj = findRemote("RemoteHotelDao");

		Log.d("fetch hotel dao remote obj " + (hotelDaoRemoteObj != null ? "succeed" : "failed"));
	}
	
	private void fetchCommentDaoRemoteObj() {
		hotelDaoRemoteObj = findRemote("RemoteCommentDao");

		Log.d("fetch comment dao remote obj " + (commentDaoRemoteObj != null ? "succeed" : "failed"));
	}

	private void fetchPromotionDaoRemoteObj() {
		promotionDaoRemoteObj = findRemote("RemotePromotionDao");

		Log.d("fetch promotion dao remote obj " + (promotionDaoRemoteObj != null ? "succeed" : "failed"));
	}

	private Remote hotelDaoRemoteObj;
	private Remote queryDaoRemoteObj;
	private Remote promotionDaoRemoteObj;
	private Remote commentDaoRemoteObj;

	public RemoteHotelDao getRemoteHotelDao() {
		return (RemoteHotelDao) hotelDaoRemoteObj;
	}

	public RemoteCreditQueryDao getRemoteCreditQueryDao() {
		return (RemoteCreditQueryDao) queryDaoRemoteObj;
	}

	public RemotePromotionQueryDao getRemotePromotionQueryDao() {
		return (RemotePromotionQueryDao) queryDaoRemoteObj;
	}

	public RemoteHotelPromotionDao getRemoteHotelPromotionDao() {
		return (RemoteHotelPromotionDao) promotionDaoRemoteObj;
	}
	
//	public RemoteCommentDao getRemoteCommentDao() {
//		return (RemoteCommentDao) commentDaoRemoteObj;
//	}

//	public RemoteWebsitePromotionDao getRemoteWebsitePromotionDao() {
//		return (RemoteWebsitePromotionDao) promotionDaoRemoteObj;
//	}

	private static final int PORT = 8888;
	private static final String URL_HEADER = new StringBuilder("rmi://localhost:").append(PORT).append("/").toString();

	private static final Remote findRemote(String name) {
		final String url = new StringBuilder(URL_HEADER).append(name).toString();
		try {
			return Naming.lookup(url);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			Log.e("RMI Exception", e);
		}
		return null;
	}
}
