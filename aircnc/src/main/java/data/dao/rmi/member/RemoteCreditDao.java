package data.dao.rmi.member;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;

/**
 * Remote interface for CreditDao
 * 
 * @see data.dao.member.CreditDao
 * @author ClevelandAlto
 *
 */
public interface RemoteCreditDao extends Remote {
	public MemberPo changeCredit(final CreditChangePo changePo) throws RemoteException;
}
