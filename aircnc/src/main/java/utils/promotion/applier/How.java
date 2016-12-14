package utils.promotion.applier;

import utils.info.order.OrderInfo;
import utils.promotion.Describer;
import utils.property.ParametersList;

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
		return discount.makeDiscount(params, order);
	}

	public String describe(ParametersList params) {
		return describer.describe(params);
	}
}
