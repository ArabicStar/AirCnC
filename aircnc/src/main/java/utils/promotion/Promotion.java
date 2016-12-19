package utils.promotion;

import utils.info.order.OrderInfo;
import utils.promotion.applier.Applier;
import utils.promotion.trigger.Trigger;

public class Promotion {
	private Applier applier;
	private Trigger trigger;

	public Promotion(Applier applier, Trigger trigger) {
		this.applier = applier;
		this.trigger = trigger;
	}

	public boolean canApplyTo(OrderInfo order, OrderRelatedInfoHelper helper) {
		return trigger.test(order, helper);
	}

	public OrderInfo applyTo(OrderInfo order) {
		return applier.applyTo(order);
	}

	public String description() {
		return trigger.when() + " " + applier.how();
	}

	@Override
	public String toString() {
		return applier.toString() + "@v@" + trigger.toString();
	}

	/**
	 * @return applier
	 */
	public Applier getApplier() {
		return applier;
	}

	/**
	 * @return trigger
	 */
	public Trigger getTrigger() {
		return trigger;
	}

}
