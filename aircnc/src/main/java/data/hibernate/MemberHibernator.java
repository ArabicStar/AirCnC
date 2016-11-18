package data.hibernate;

import po.member.MemberPo;

/**
 * Direct operator on database for member info.<br>
 * Public methods should only be used by MemberDao. Please <b>NOT</b> used in
 * client code.<br>
 * 
 * @see data.dao.MemberDao
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberHibernator {
	/**
	 * Find a member by id.<br>
	 * 
	 * @param id
	 * @return a MemberPo<br>
	 *         if not exist, return null<br>
	 */
	public MemberPo findMember(int id);

	/**
	 * Delete a member by id.<br>
	 * 
	 * @param id
	 * @return operation result <br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean deleteMember(int id);

	/**
	 * Update a member.<br>
	 * The id is assumed being contained in po.<br>
	 * 
	 * @param po
	 *            MemberPo to be updated.<br>
	 * @return operation result<br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean updateMember(MemberPo po);

	/**
	 * Add a member<br>
	 * 
	 * @param po
	 *            MemberPo to be added.<br>
	 * @return operation result<br>
	 *         Fail if id has existed.<br>
	 */
	public boolean addMember(MemberPo po);

	/**
	 * Query an id exists or not
	 * 
	 * @param id
	 * @return whether given id exists or not<br>
	 */
	public boolean existId(int id);
}
