package utils.promotion.trigger;

import utils.info.order.OrderInfo;
import utils.promotion.OrderRelatedInfoHelper;
import utils.property.ParametersList;

@FunctionalInterface
public interface Criterion {
	public boolean test(final ParametersList params, OrderInfo order, final OrderRelatedInfoHelper helper);
}
