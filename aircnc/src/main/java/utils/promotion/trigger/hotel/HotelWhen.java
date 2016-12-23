package utils.promotion.trigger.hotel;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.Describer;
import utils.promotion.trigger.Criterion;
import utils.promotion.trigger.TriggerTemplates;

public enum HotelWhen {
	BIRTHDAY, //
	MULTI_ROOMS, //
	ENTERPRISE, //
	DURING_PERIOD;//

	private Describer describer;
	private Criterion criterion;

	private HotelWhen() {
		describer = TriggerTemplates.getDescriber(this);
		criterion = TriggerTemplates.getCritierion(this);
	}

	public String describe(ParametersList params) {
		return describer.describe(params);
	}

	public boolean test(final ParametersList params, OrderInfo order) {
		return criterion.test(params, order);
	}
}
