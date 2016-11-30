package service.member;

import vo.order.OrderVo;

/**
 * Credit value change strategy interface.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface CreditStrategy {
	/**
	 * @param chargeMoney
	 *            money charged <br>
	 * @return credit increment <br>
	 */
	public int getChargeIncrement(int chargeMoney);

	/**
	 * @param vo
	 *            order vo <br>
	 * @return credit increment <br>
	 */
	public int getExecutionIncrement(OrderVo vo);

	/**
	 * @param vo
	 *            order vo <br>
	 * @return credit reduction <br>
	 */
	public int getCancelReduction(OrderVo vo);

	/**
	 * @param vo
	 *            order vo <br>
	 * @return credit reduction
	 */
	public int getOverdueReduction(OrderVo vo);

	/**
	 * @param vo
	 *            order vo <br>
	 * @return credit recovery
	 */
	public int getAppealRecovery(OrderVo vo);

	/**
	 * @param vo
	 *            order vo <br>
	 * @return credit recovery
	 */
	public int getDelayRecovery(OrderVo vo);
}
