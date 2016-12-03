package data.dao.query;

import java.util.List;

import po.member.credit.CreditChangePo;

/**
 * Dao fo credit change query.<br>
 * It's a logical dao, actually the implemention is appointed to CreditDao.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface CreditQueryDao {
	/**
	 * Search credit changes of an appointed member by given id. <br>
	 * 
	 * @param memberId
	 *            member's id
	 * @return
	 *         <ul>
	 *         <li><b>null</b>
	 *         <ul>
	 *         <li>if member id not exists</li>
	 *         <li>or query fails.</li>
	 *         </ul>
	 *         <li><b>list of credit changes</b>
	 *         <ul>
	 *         <li>if query succeed.</li>
	 *         </ul>
	 * 
	 * @throws IllegalArgumentException
	 *             given member id is not invalid id string
	 */
	public List<CreditChangePo> searchByMemberId(String memberId);
}
