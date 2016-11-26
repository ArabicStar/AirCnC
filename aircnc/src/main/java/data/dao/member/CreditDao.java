package data.dao.member;

import po.member.MemberPo;
import po.member.credit.CreditChangePo;

/**
 * Dao of credit.<br>
 * Only offer <i>changeCredit</i> method, because CreditChangePo is
 * never-deleted.<br>
 * 
 * @author ClevelandAlto
 * @see CreditChangePo
 */
public interface CreditDao {
	/**
	 * Change credit value.Operations going to happen include
	 * <ul>
	 * <li>Update of member credit
	 * <li>Insertion of a credit change record
	 * </ul>
	 * 
	 * @param aChange
	 *            CreditChangePo instance <br>
	 * @return
	 *         <ul>
	 *         <li>null
	 *         <ul>
	 *         <li>if given CreditChangePo instance is null
	 *         <li>or related member account doesn't exist
	 *         <li>or either or both operation failed.
	 *         </ul>
	 *         <li>InvalidMemberPo instance
	 *         <ul>
	 *         <li>if current credit value in given CreditChangePo is not
	 *         consistent with related MemberPo.
	 *         </ul>
	 *         <li>changed MemberPo instance
	 *         <ul>
	 *         <li>all operations succeed. Then changed MemberPo will be
	 *         returned.
	 *         </ul>
	 *         </ul>
	 */
	public MemberPo changeCredit(CreditChangePo aChange);
}
