package data.dao.rmi.member;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.member.MemberPo;

/**
 * Remote interface for member dao.<br>
 * 
 * @see data.dao.member.MemberDao
 * @author ClevelandAlto
 *
 */
public interface RemoteMemberDao extends Remote {

	public boolean addMember(final MemberPo po) throws RemoteException;

	public boolean deleteMember(final String id) throws RemoteException;

	public boolean updateMember(final MemberPo po) throws RemoteException;

	public MemberPo findMember(final String id) throws RemoteException;

	public boolean existsMember(final String id) throws RemoteException;
}
