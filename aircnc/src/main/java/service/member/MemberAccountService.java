package service.member;

import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * Interface for account operation<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberAccountService {
	/**
	 * Register a new member account<br>
	 * 
	 * @param newMember
	 *            member info without id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of new member<br>
	 *         Else, invalid member vo<br>
	 */
	public MemberVo register(MemberVoBuilder newMember, int passwordHash);

	/**
	 * Login a member account
	 * 
	 * @param id
	 *            member id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of logined member<br>
	 *         If password wrong, return invalid member vo<br>
	 *         If account not exists yet, return null
	 */
	public MemberVo login(String id, int passwordHash);

	/**
	 * Logout an member account<br>
	 * 
	 * @return logout operation result
	 */
	public boolean logout();

	/**
	 * Get status of login<br>
	 * 
	 * @return status of login
	 */
	public boolean isLogined();

	/**
	 * Get logined member's vo<br>
	 * 
	 * @return logined member's vo
	 */
	public MemberVo getLoginedMember();

	/**
	 * Inquiry an id exists or not<br>
	 * 
	 * @param id
	 *            An id
	 * @return appointed id exists or not
	 */
	public boolean existsMember(String id);
}
