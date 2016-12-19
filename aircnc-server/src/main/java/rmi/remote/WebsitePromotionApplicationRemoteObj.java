package rmi.remote;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.dao.rmi.promotion.RemoteWebsitePromotionApplicationService;
import rmi.RemoteHelper;
import service.impl.promotion.WebsitePromotionApplicationManager;
import service.promotion.WebsitePromotionApplicationService;
import utils.info.order.OrderInfo;

/*final*/
public class WebsitePromotionApplicationRemoteObj extends UnicastRemoteObject
		implements RemoteWebsitePromotionApplicationService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8824920954814600424L;
	/* Singleton */
	private static WebsitePromotionApplicationRemoteObj instance;

	public static void launch() throws RemoteException {
		if (instance != null)
			throw duplicateSingletonEx();

		final WebsitePromotionApplicationService service = WebsitePromotionApplicationManager.getInstance();

		instance = new WebsitePromotionApplicationRemoteObj(service);

		RemoteHelper.bindRemoteObj("RemoteWebsitePromotionApplicationService", instance);
	}

	/* Singleton */

	/**
	 * @param service
	 * @throws RemoteException
	 */
	private WebsitePromotionApplicationRemoteObj(WebsitePromotionApplicationService service) throws RemoteException {
		super();
		this.service = service;
	}

	private WebsitePromotionApplicationService service;

	@Override
	public OrderInfo applyPromotion(OrderInfo info) throws RemoteException {
		return service.applyPromotion(info);
	}
}
