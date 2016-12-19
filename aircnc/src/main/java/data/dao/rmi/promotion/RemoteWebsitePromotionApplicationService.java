package data.dao.rmi.promotion;

import java.rmi.Remote;
import java.rmi.RemoteException;

import utils.info.order.OrderInfo;

public interface RemoteWebsitePromotionApplicationService extends Remote {
	public OrderInfo applyPromotion(OrderInfo info) throws RemoteException;
}
