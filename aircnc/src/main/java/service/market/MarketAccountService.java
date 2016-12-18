package service.market;

import utils.info.market.MarketInfo;

/**
 * Interface for account operation<br>
 *
 * @author ParanoiaSun
 *
 */
public interface MarketAccountService {

	/**
	 * Login a market account
	 *
	 * @param id
	 *            market id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of logined market<br>
	 *         If password wrong, return invalid market vo<br>
	 *         If account not exists yet, return null
	 */
	public MarketInfo login(final String id, final int passwordHash);

	/**
	 * Logout a market account<br>
	 *
	 * @return logout operation result
	 */
	public boolean logout();

	/**
	 * Get status of login<br>
	 *
	 * @return status of login
	 */
	public boolean isLoggedin();

	/**
	 * Refresh currently logged in market account info.<br>
	 */
	public MarketInfo refreshCurrentAccount();

	/**
	 * Get logged market's vo<br>
	 * 
	 * @return logged market's vo
	 */
	public MarketInfo getCurrentAccount();

	/**
	 * Inquiry an id exists or not<br>
	 * <b>NOTICE</b>: This method will cause a database query. So when you are
	 * going to get a MarketVo instance immediately, avoid to use this method.
	 * Instead, test the MarketVo instance got is null or not to determined if
	 * the id exists or not<br>
	 * 
	 * @param id
	 *            An id
	 * @return appointed id exists or not
	 */
	public boolean existsMarket(final String id);
}
