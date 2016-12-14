package utils.promotion.trigger.website;

import utils.info.order.OrderInfo;
import utils.promotion.OrderRelatedInfoHelper;
import utils.promotion.trigger.Trigger;
import utils.property.ParametersList;

public class WebsiteTrigger implements Trigger {

	private ParametersList params;
	private WebsiteWhen when;

	public WebsiteTrigger(WebsiteWhen when, ParametersList params) {
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
