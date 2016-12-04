package service.hotel;

import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;

public interface HotelAccountService {
	/**
	 * Register a new hotel account<br>
	 * 
	 * @param newHotel
	 *            hotel info without id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of new hotel<br>
	 *         Else, invalid hotel vo<br>
	 */
	public HotelInfo register(HotelVoBuilder newHotel, final int passwordHash);

	/**
	 * Login a hotel account
	 * 
	 * @param id
	 *            hotel id<br>
	 * @param passwordHash
	 *            password hashcode<br>
	 * @return If succeed, return vo of logined hotel<br>
	 *         If password wrong, return invalid hotel vo<br>
	 *         If account not exists yet, return null
	 */
	public HotelInfo login(final int id, final int passwordHash);

	/**
	 * Logout an hotel account<br>
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
	 * Refresh currently logined hotel account info.<br>
	 */
	public void refreshCurrentAccount();

	/**
	 * Get logined hotel's vo<br>
	 * 
	 * @return logined hotel's vo
	 */
	public HotelInfo getCurrentAccount();

	/**
	 * Inquiry an id exists or not<br>
	 * <b>NOTICE</b>: This method will cause a database query. So when you are
	 * going to get a HotelVo instance immediately, avoid to use this method.
	 * Insteadly, test the HotelVo instance got is null or not to determined if
	 * the id exists or not<br>
	 * 
	 * @param id
	 *            An id
	 * @return appointed id exists or not
	 */
	public boolean existsHotel(final int id);
}
