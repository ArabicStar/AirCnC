package service.impl.member;

import service.member.CreditStrategy;
import vo.order.OrderVo;

public class SimpleCreditStrategyAdapter implements CreditStrategy {
	private static final int CHARGE_POW = 10;
	private static final double EXECUTION_POW = 1.0;
	private static final double CANCEL_POW = 0.5;
	private static final double OVERDUE_POW = 1.0;
	private static final double APPEAL_POW = 1.0;
	private static final double DELAY_POW = 0.5;

	@Override
	public int getChargeIncrement(int chargeMoney) {
		if (chargeMoney <= 0)
			throw new IllegalArgumentException(
					"SimpleCreditStrategyAdapter.getChargeIncrement - Non-positive Money amount: " + chargeMoney);

		return chargeMoney * CHARGE_POW;
	}

	@Override
	public int getExecutionIncrement(OrderVo vo) {
		return (int) (vo.getPrice() * EXECUTION_POW);
	}

	@Override
	public int getCancelReduction(OrderVo vo) {
		return -(int) (vo.getPrice() * CANCEL_POW);
	}

	@Override
	public int getOverdueReduction(OrderVo vo) {
		return -(int) (vo.getPrice() * OVERDUE_POW);
	}

	@Override
	public int getAppealRecovery(OrderVo vo) {
		return (int) (vo.getPrice() * APPEAL_POW);
	}

	@Override
	public int getDelayRecovery(OrderVo vo) {
		return (int) (vo.getPrice() * DELAY_POW);
	}

}
