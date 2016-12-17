package data.dao.rmi.promotion;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.promotion.WebsitePromotionPo;

public interface RemoteWebsitePromotionDao extends Remote{
	public boolean addWebsitePromotion(WebsitePromotionPo po)throws RemoteException;

	public boolean deleteWebsitePromotion(long id)throws RemoteException;

	public boolean updateWebsitePromotion(WebsitePromotionPo po)throws RemoteException;

	public WebsitePromotionPo findWebsitePromotion(long id) throws RemoteException;
}
