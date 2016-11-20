package service.member;

import vo.member.MemberVo;
import vo.order.OrderVo;

/**
 * Service for member's credit operation<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberCreditService {
	/**
	 * @param id
	 *            Member' ID
	 * @param creditDelta
	 *            Value change on credit, with sign
	 * @param action
	 *            Action of credit change
	 * @return Vo of member whose credit was changed.<br>
	 *         if appointed member not exists or operation fails due to other
	 *         cause, return null<br>
	 */

	public MemberVo gainByCharge(int money, String memberId);

	public MemberVo gainByOrderExecution(OrderVo order);

	public MemberVo reduceByOverdue(OrderVo order);

	public MemberVo reduceByCancel(OrderVo order);

	public MemberVo recoverByDelay(OrderVo order);

	public MemberVo recoverByAppeal(OrderVo order);
}
