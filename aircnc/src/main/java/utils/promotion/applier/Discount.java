package utils.promotion.applier;

import java.io.Serializable;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;

@FunctionalInterface
public interface Discount extends Serializable{
	public OrderInfo makeDiscount(ParametersList params, OrderInfo order);
}
