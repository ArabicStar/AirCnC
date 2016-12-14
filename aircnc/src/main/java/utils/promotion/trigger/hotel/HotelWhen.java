package utils.promotion.trigger.hotel;

import utils.info.order.OrderInfo;
import utils.promotion.Describer;
import utils.promotion.OrderRelatedInfoHelper;
import utils.promotion.trigger.Criterion;
import utils.promotion.trigger.TriggerTemplates;
import utils.property.ParametersList;

public enum HotelWhen {
	BIRTHDAY, //
	MULTI_ROOMS, //
	ENTERPRISE, //
	PERIOD;//

	private Describer describer;
	private Criterion criterion;

	private HotelWhen() {
		describer = TriggerTemplates.getDescriber(this);
		criterion = TriggerTemplates.getCritierion(this);
	}

	public String describe(ParametersList params) {
		return describer.describe(params);
	}

	public boolean test(final ParametersList params, OrderInfo order, final OrderRelatedInfoHelper helper) {
		return criterion.test(params, order, helper);
	}
}
