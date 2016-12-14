package utils.promotion.applier;

import utils.info.order.OrderInfo;
import utils.property.ParametersList;

public class Applier {
	private ParametersList params;
	private How how;

	public Applier(ParametersList params, How how) {
		this.params = params;
		this.how = how;
	}

	public OrderInfo applyTo(OrderInfo order) {
		return how.applyTo(params, order);
	}

	public String how() {
		return how.describe(params);
	}
}
