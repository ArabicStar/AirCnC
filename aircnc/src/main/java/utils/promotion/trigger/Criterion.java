package utils.promotion.trigger;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.OrderRelatedInfoHelper;

@FunctionalInterface
public interface Criterion {
	public boolean test(final ParametersList params, OrderInfo order, final OrderRelatedInfoHelper helper);
}
