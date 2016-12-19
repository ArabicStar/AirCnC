package utils.promotion.applier;

import static utils.exception.StaticExceptionFactory.*;
import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.Describer;

public enum How {
	CONST, //
	PERCENT_OFF;

	private Discount discount;
	private Describer describer;

	private How() {
		discount = ApplierTemplates.getDiscount(this);
		describer = ApplierTemplates.getDescriber(this);
	}

	public OrderInfo applyTo(ParametersList params, OrderInfo order) {
		try {
			return discount.makeDiscount(params, order);
		} catch (Exception e) {
			throw illegalArgEx("Wrong promotion parameters");
		}
	}

	public String describe(ParametersList params) {
		return describer.describe(params);
	}
}
