package utils.promotion.trigger.hotel;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.trigger.Trigger;

public class HotelTrigger implements Trigger {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3865683247216238692L;
	private ParametersList params;
	private HotelWhen when;

	public HotelTrigger(HotelWhen when, ParametersList params) {
		this.when = when;
		this.params = params;
	}

	@Override
	public String when() {
		return when.describe(params);
	}

	@Override
	public boolean test(OrderInfo order) {
		return when.test(params, order);
	}

	@Override
	public String toString() {
		return new StringBuilder("Hotel@^@").append(when.name()).append("@^@").append(params.toString()).toString();
	}

	@Override
	public String name() {
		return when.name();
	}
}
