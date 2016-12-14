package utils.promotion.trigger.hotel;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.OrderRelatedInfoHelper;
import utils.promotion.trigger.Trigger;

public class HotelTrigger implements Trigger {
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
	public boolean test(OrderInfo order, OrderRelatedInfoHelper helper) {
		return when.test(params, order, helper);
	}

}
