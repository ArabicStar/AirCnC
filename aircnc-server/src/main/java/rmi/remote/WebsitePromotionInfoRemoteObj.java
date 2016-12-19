package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import data.dao.rmi.promotion.RemoteWebsitePromotionInfoService;
import rmi.RemoteHelper;
import service.impl.promotion.WebsitePromotionInfoManager;
import service.promotion.WebsitePromotionInfoService;
import vo.promotion.PromotionVo;

public class WebsitePromotionInfoRemoteObj extends UnicastRemoteObject implements RemoteWebsitePromotionInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750176374782966321L;
	/* Singleton */
	private static WebsitePromotionInfoRemoteObj instance;

	public static void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final WebsitePromotionInfoService infoService = WebsitePromotionInfoManager.getInstance();

		instance = new WebsitePromotionInfoRemoteObj(infoService);

		RemoteHelper.bindRemoteObj("RemoteWebsitePromotionInfoService", instance);
	}
	/* Singleton */

	private WebsitePromotionInfoService infoService;

	protected WebsitePromotionInfoRemoteObj(WebsitePromotionInfoService infoService) throws RemoteException {
		super();
		this.infoService = infoService;
	}

	@Override
	public Set<PromotionVo> getUserAvailablePromotions() throws RemoteException {
		return infoService.getUserAvailablePromotions();
	}

	@Override
	public void refreshBuffer() throws RemoteException {
		infoService.refreshBuffer();
	}

}
