package data.dao.rmi.promotion;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.promotion.HotelPromotionPo;

public interface RemoteHotelPromotionDao extends Remote {
	public boolean addHotelPromotion(HotelPromotionPo po) throws RemoteException;

	public boolean deleteHotelPromotion(long id) throws RemoteException;

	public boolean updateHotelPromotion(HotelPromotionPo po) throws RemoteException;

	public HotelPromotionPo findHotelPromotion(long id) throws RemoteException;
}
