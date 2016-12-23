package utils.promotion.trigger.website;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.trigger.Trigger;

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
	public boolean test(OrderInfo order) {
		return when.test(params, order);
	}

	@Override
	public String toString() {
		return new StringBuilder("Website@^@").append(when.name()).append("@^@").append(params.toString()).toString();
	}
}
