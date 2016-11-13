package service.member;

import utils.info.member.credit.ChangeAction;
import vo.member.MemberVo;

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
	public MemberVo changeCredit(String id, int creditDelta, ChangeAction action);
}
