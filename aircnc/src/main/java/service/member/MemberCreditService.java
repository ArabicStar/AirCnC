package service.member;

import vo.member.MemberVo;
import vo.order.OrderVo;

/**
 * Service for member's credit operation<br>
 * Remember call MemberAccountService.refreshCurrentAccount() in client for
 * member after credit change complete.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberCreditService {
	/**
	 * Credit gain by charge.<br>
	 * 
	 * @param money
	 *            amount of money
	 * @param memberId
	 *            member id
	 * @return changed MemberVo
	 */
	public MemberVo gainByCharge(int money, String memberId);

	public MemberVo gainByOrderExecution(OrderVo order);

	public MemberVo reduceByOverdue(OrderVo order);

	public MemberVo reduceByCancel(OrderVo order);

	public MemberVo recoverByDelay(OrderVo order);

	public MemberVo recoverByAppeal(OrderVo order);
}
