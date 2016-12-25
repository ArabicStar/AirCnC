package utils.promotion.trigger;

import java.io.Serializable;

import utils.info.order.OrderInfo;

public interface Trigger extends Serializable{
	public abstract boolean test(OrderInfo order);

	public abstract String when();
}
