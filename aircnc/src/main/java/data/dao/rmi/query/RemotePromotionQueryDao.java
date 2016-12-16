package data.dao.rmi.query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import po.promotion.PromotionPo;

public interface RemotePromotionQueryDao extends Remote{
	public Set<PromotionPo> getHotelAllPromotions(int hotelId)throws RemoteException;

	public Set<PromotionPo> getWebsiteAllPromotions()throws RemoteException;
}
