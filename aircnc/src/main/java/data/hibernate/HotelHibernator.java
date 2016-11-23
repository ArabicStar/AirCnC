package data.hibernate;

import po.hotel.HotelPo;

/**
 * Direct operator on database for hotel info.<br>
 * Public methods should only be used by HotelDao. Please <b>NOT</b> used in
 * client code.<br>
 * 
 * @see data.dao.HotelDao
 * 
 * @author jqwu
 *
 */
public interface HotelHibernator {
	/**
	 * Find a hotel by id.<br>
	 * 
	 * @param id
	 * @return a HotelPo<br>
	 *         if not exist, return null<br>
	 */
	public HotelPo findHotel(int id);

	/**
	 * Delete a hotel by id.<br>
	 * 
	 * @param id
	 * @return operation result <br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean deleteHotel(int id);

	/**
	 * Update a hotel.<br>
	 * The id is assumed being contained in po.<br>
	 * 
	 * @param po
	 *            HotelPo to be updated.<br>
	 * @return operation result<br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean updateHotel(HotelPo po);

	/**
	 * Add a hotel<br>
	 * 
	 * @param po
	 *            HotelPo to be added.<br>
	 * @return operation result<br>
	 *         Fail if id has existed.<br>
	 */
	public boolean addHotel(HotelPo po);

	/**
	 * Query an id exists or not
	 * 
	 * @param id
	 * @return whether given id exists or not<br>
	 */
	public boolean existId(int id);
}
