package data.dao.rmi.promotion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import vo.promotion.PromotionVo;

public interface RemoteWebsitePromotionInfoService extends Remote {
	public Set<PromotionVo> getUserAvailablePromotions() throws RemoteException;

	public void refreshBuffer() throws RemoteException;
}
