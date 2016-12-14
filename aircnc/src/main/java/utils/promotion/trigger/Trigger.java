package utils.promotion.trigger;

import utils.info.order.OrderInfo;
import utils.promotion.OrderRelatedInfoHelper;

public interface Trigger {

	public abstract boolean test(OrderInfo order, OrderRelatedInfoHelper helper);

	public abstract String when();
}
