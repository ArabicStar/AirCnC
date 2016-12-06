package data.dao.member;

import java.rmi.Remote;

import po.member.MemberPo;

/**
 * Dao of member.<br>
 * Offer standard CRUD methods, plus a existence checker for convinience.<br>
 * <br>
 * 
 * <b>Tips:</b><br>
 * Implemention of {@code existsMember(String id)} performs similar as
 * {@code findMember(String id)} - get a po from database then check if is null
 * or not. It means it causes a database query. So if you will call
 * {@code findMember(String id)} shortly after, then check returned po is null
 * or not instead of using {@code existsMember(String id)}. Use it just in
 * condition where a member po is not needed.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberDao extends Remote {
	/**
	 * Add a new member by given po.<br>
	 * 
	 * @param po
	 *            po of new member
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>po is null</li>
	 *         <li>if member of this po has already existed</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 */
	public boolean addMember(final MemberPo po);

	/**
	 * Delete member by given id.<br>
	 * 
	 * @param id
	 *            id of deleted member
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>operation fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public boolean deleteMember(final String id);

	/**
	 * Update member by given po.<br>
	 * 
	 * @param po
	 *            po of updated member
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>operation fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 */
	public boolean updateMember(final MemberPo po);

	/**
	 * Get member by given id.<br>
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         <li><b>null</b>
	 *         <ul>
	 *         <li>account doesn't exist</li>
	 *         <li>or query fails</li>
	 *         </ul>
	 *         <li><b>MemberPo instance</b></li>
	 *         <ul>
	 *         <li>query succeed</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public MemberPo findMember(final String id);

	/**
	 * Check if given member exists..<br>
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>query fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>id exists</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public boolean existsMember(final String id);
}
