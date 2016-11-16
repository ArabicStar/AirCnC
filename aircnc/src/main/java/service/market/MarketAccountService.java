package service.market;

import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

/**
 * Interface for account operation<br>
 *
 * @author ParanoiaSun
 *
 */
public interface MarketAccountService {
	/**
	 * Register a new market account<br>
	 *
	 * @param newMarket
	 *            market info without id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of new market<br>
	 *         Else, invalid market vo<br>
	 */
	public MarketVo register(MarketVoBuilder newMarket, int passwordHash);

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
	public MarketVo login(String id, int passwordHash);

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
	public boolean isLogined();

	/**
	 * Get logined market's vo<br>
	 *
	 * @return logined market's vo
	 */
	public MarketVo getLoginedMarket();

	/**
	 * Inquiry an id exists or not<br>
	 *
	 * @param id
	 *            An id
	 * @return appointed id exists or not
	 */
	public boolean existsMarket(String id);
}
