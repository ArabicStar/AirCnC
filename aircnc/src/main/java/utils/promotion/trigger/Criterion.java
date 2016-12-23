package utils.promotion.trigger;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;

@FunctionalInterface
public interface Criterion {
	public boolean test(final ParametersList params, OrderInfo order);
}
