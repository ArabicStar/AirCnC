package utils.promotion;

import utils.info.order.OrderInfo;

public abstract class Promotion<T extends Trigger> {
	private Applier applier;
	private T trigger;

	public boolean canApplyTo(OrderInfo order) {
		return trigger.test(order);
	}

	public OrderInfo applyTo(OrderInfo order) {
		return applier.applyTo(order);
	}

	public String description() {
		return trigger.when() + applier.how();
	}
}
