package utils.promotion;

import java.io.Serializable;

import utils.info.order.OrderInfo;
import utils.promotion.applier.Applier;
import utils.promotion.trigger.Trigger;

public class Promotion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4111719648815680772L;
	private Applier applier;
	private Trigger trigger;

	public Promotion(Applier applier, Trigger trigger) {
		this.applier = applier;
		this.trigger = trigger;
	}

	public boolean canApplyTo(OrderInfo order) {
		return trigger.test(order);
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
