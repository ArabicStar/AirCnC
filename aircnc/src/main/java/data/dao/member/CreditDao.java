package data.dao.member;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;

/**
 * Dao of credit.<br>
 * Only offer method for inserting, because CreditChangePo is never deleted or
 * changed since being created ever.<br>
 * 
 * @author ClevelandAlto
 * @see CreditChangePo
 */
public interface CreditDao {
	/**
	 * Change credit value. Operations going to happen include
	 * <ul>
	 * <li>Update of member credit
	 * <li>Insertion of a credit change record
	 * </ul>
	 * 
	 * @param changePo
	 *            CreditChangePo instance <br>
	 * @return
	 *         <ul>
	 *         <li><b>null</b>
	 *         <ul>
	 *         <li>if given CreditChangePo instance is null
	 *         <li>or related member account doesn't exist
	 *         <li>or either or both operations failed.
	 *         </ul>
	 *         <li><b>InvalidMemberPo instance</b>
	 *         <ul>
	 *         <li>if {@code beforeCredit}'s value in given CreditChangePo is
	 *         not consistent with {@code credit}'s value in related MemberPo.
	 *         </ul>
	 *         <li><b>changed MemberPo instance</b>
	 *         <ul>
	 *         <li>if all operations succeed.
	 *         </ul>
	 *         </ul>
	 */
	public MemberPo changeCredit(final CreditChangePo changePo);
}
