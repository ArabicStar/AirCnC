package utils.promotion.trigger;

import java.io.Serializable;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;

@FunctionalInterface
public interface Criterion extends Serializable{
	public boolean test(final ParametersList params, OrderInfo order);
}
