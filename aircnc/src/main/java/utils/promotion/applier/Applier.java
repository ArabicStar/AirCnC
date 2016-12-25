package utils.promotion.applier;

import java.io.Serializable;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;

public class Applier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4746280171334749151L;
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

	public String toString() {
		return new StringBuilder(how.name()).append("@^@").append(params.toString()).toString();
	}
}
