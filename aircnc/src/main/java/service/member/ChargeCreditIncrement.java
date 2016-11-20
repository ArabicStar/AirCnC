package service.member;

/**
 * Charge credit value strategy interface.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface ChargeCreditIncrement {
	/**
	 * @param chargeMoney
	 *            money charged <br>
	 * @return credit increment <br>
	 */
	public int getCreditIncrement(int chargeMoney);
}
