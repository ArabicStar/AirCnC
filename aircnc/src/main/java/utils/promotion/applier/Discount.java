package utils.promotion.applier;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;

@FunctionalInterface
public interface Discount {
	public OrderInfo makeDiscount(ParametersList params, OrderInfo order);
}
