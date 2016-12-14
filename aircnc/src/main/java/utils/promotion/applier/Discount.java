package utils.promotion.applier;

import utils.info.order.OrderInfo;
import utils.property.ParametersList;

@FunctionalInterface
public interface Discount {
	public OrderInfo makeDiscount(ParametersList params,OrderInfo order);
}
