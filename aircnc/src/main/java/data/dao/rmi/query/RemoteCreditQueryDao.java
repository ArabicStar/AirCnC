package data.dao.rmi.query;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.member.credit.CreditChangePo;

/**
 * Remote interface for {@code CreditQueryDao}
 * 
 * @see data.dao.query.CreditQueryDao
 * @author ClevelandAlto
 *
 */
public interface RemoteCreditQueryDao extends Remote {
	public List<CreditChangePo> searchByMemberId(String memberId) throws RemoteException;
}
