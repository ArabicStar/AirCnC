package data.dao.market;

import po.market.MarketPo;

/**
 * Dao of market.<br>
 * Offer standard CRUD methods, plus a existence checker for convenience.<br>
 * <br>
 * 
 * <b>Tips:</b><br>
 * Implementation of {@code existsMarket(String id)} performs similar as
 * {@code findMarket(String id)} - get a po from database then check if is null
 * or not. It means it causes a database query. So if you will call
 * {@code findMarket(String id)} shortly after, then check returned po is null
 * or not instead of using {@code existsMarket(String id)}. Use it just in
 * condition where a market po is not needed.<br>
 * 
 * @author paranoia
 *
 */
public interface MarketDao {
	
	/**
	 * Add a new market by given po.<br>
	 * 
	 * @param po
	 *            po of new market
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>po is null</li>
	 *         <li>if market of this po has already existed</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 */
	public boolean addMarket(final MarketPo po);
	
	/**
	 * Delete market by given id.<br>
	 * 
	 * @param id
	 *            id of deleted market
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>operation fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public boolean deleteMarket(String id);

	/**
	 * Update market by given po.<br>
	 * 
	 * @param po
	 *            po of updated market
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>operation fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>operation succeed</li>
	 *         </ul>
	 *         </ul>
	 */
	public boolean updateMarket(MarketPo po);
	
	/**
	 * Get market by given id.<br>
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         <li><b>null</b>
	 *         <ul>
	 *         <li>account doesn't exist</li>
	 *         <li>or query fails</li>
	 *         </ul>
	 *         <li><b>MarketPo instance</b></li>
	 *         <ul>
	 *         <li>query succeed</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public MarketPo findMarket(String id);
	
	/**
	 * Check if given market exists.<br>
	 * 
	 * @param id
	 * @return
	 *         <ul>
	 *         <li><b>false</b>
	 *         <ul>
	 *         <li>id doesn't exist</li>
	 *         <li>query fails</li>
	 *         </ul>
	 *         <li><b>true</b></li>
	 *         <ul>
	 *         <li>id exists</li>
	 *         </ul>
	 *         </ul>
	 * @throws IllegalArgumentException
	 *             Id is invalid
	 */
	public boolean existsMarket(String id);
}
