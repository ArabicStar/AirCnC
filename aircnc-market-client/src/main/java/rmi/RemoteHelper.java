package rmi;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import data.dao.rmi.hotel.RemoteHotelDao;
import data.dao.rmi.market.RemoteMarketDao;
import data.dao.rmi.member.RemoteMemberDao;
import data.dao.rmi.promotion.RemoteWebsitePromotionDao;
import data.dao.rmi.query.RemoteCommentQueryDao;
import data.dao.rmi.query.RemotePromotionQueryDao;
import utils.logger.Log;

public final class RemoteHelper {
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
		fetchMemberDaoRemoteObj();
		fetchHotelDaoRemoteObj();
		fetchMarketDaoRemoteObj();
		fetchQueryDaoRemoteObj();
	}

	private void fetchQueryDaoRemoteObj() {
		queryDaoRemoteObj = findRemote("RemoteQueryDao");

		Log.d("fetch query dao remote obj " + (queryDaoRemoteObj != null ? "succeed" : "failed"));
	}

	private void fetchMemberDaoRemoteObj() {
		memberDaoRemoteObj = findRemote("RemoteMemberDao");

		Log.d("fetch member dao remote obj " + (memberDaoRemoteObj != null ? "succeed" : "failed"));
	}
	
	private void fetchHotelDaoRemoteObj() {
		hotelDaoRemoteObj = findRemote("RemoteHotelDao");

		Log.d("fetch hotel dao remote obj " + (hotelDaoRemoteObj != null ? "succeed" : "failed"));
	}
	
	private void fetchMarketDaoRemoteObj() {
		marketDaoRemoteObj = findRemote("RemoteMarketDao");

		Log.d("fetch market dao remote obj " + (marketDaoRemoteObj != null ? "succeed" : "failed"));
	}

	private Remote memberDaoRemoteObj;
	private Remote hotelDaoRemoteObj;
	private Remote marketDaoRemoteObj;
	private Remote queryDaoRemoteObj;
	private Remote promotionDaoRemoteObj;

	public RemoteMemberDao getRemoteMemberDao() {
		return (RemoteMemberDao) memberDaoRemoteObj;
	}
	
	public RemoteHotelDao getRemoteHotelDao(){
		return (RemoteHotelDao) hotelDaoRemoteObj;
	}
	
	public RemoteMarketDao getRemoteMarketDao(){
		return (RemoteMarketDao) marketDaoRemoteObj;
	}

	public RemoteCommentQueryDao getRemoteCommentQueryDao() {
		return (RemoteCommentQueryDao) queryDaoRemoteObj;
	}

	public RemotePromotionQueryDao getRemotePromotionQueryDao() {
		return (RemotePromotionQueryDao) queryDaoRemoteObj;
	}
	
	public RemoteWebsitePromotionDao getRemoteWebsitePromotionDao() {
	return (RemoteWebsitePromotionDao) promotionDaoRemoteObj;
}

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
