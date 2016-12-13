package utils.promotion;

import utils.info.order.OrderInfo;

public abstract class Applier {
	public enum How {
		PERCENT, CONST
	}

	private How how;

	protected Applier(How how) {
		this.how = how;
	}

	public abstract OrderInfo applyTo(OrderInfo Info);

	public abstract String how();
}
