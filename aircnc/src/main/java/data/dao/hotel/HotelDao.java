package data.dao.hotel;

import po.hotel.HotelPo;

public interface HotelDao {
	
	/**
	 * Find a hotel by id.<br>
	 * 
	 * @param id
	 * @return a HotelPo<br>
	 *         if not exist, return null<br>
	 */
	public HotelPo findHotelById(final int id);
	
	/**
	 * Find a hotel by name.<br>
	 * 
	 * @param name
	 * @return a HotelPo<br>
	 *         if not exist, return null<br>
	 */
	public HotelPo findHotelByName(final String name);

	/**
	 * Delete a hotel by id.<br>
	 * 
	 * @param id
	 * @return operation result <br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean deleteHotel(final int id);

	/**
	 * Update a hotel.<br>
	 * The id is assumed being contained in po.<br>
	 * 
	 * @param po
	 *            HotelPo to be updated.<br>
	 * @return operation result<br>
	 *         Fail if id does not exist.<br>
	 */
	public boolean updateHotel(final HotelPo po);

	/**
	 * Add a hotel<br>
	 * 
	 * @param po
	 *            HotelPo to be added.<br>
	 * @return operation result<br>
	 *         Fail if id has existed.<br>
	 */
	public boolean addHotel(final HotelPo po);
	
	
	/**
	 * Query an hotel exists or not
	 * 
	 * @param name
	 * @return whether given name exists or not<br>
	 */
	public boolean existName(final String name);
	
	public void updateRank();
}
