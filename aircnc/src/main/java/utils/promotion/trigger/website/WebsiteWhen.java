package utils.promotion.trigger.website;

import java.io.Serializable;

import utils.info.order.OrderInfo;
import utils.parameter.ParametersList;
import utils.promotion.Describer;
import utils.promotion.trigger.Criterion;
import utils.promotion.trigger.TriggerTemplates;

public enum WebsiteWhen implements Serializable{
	LEVEL, //
	TRADE_AREA, //
	DURING_PERIOD;//

	private Describer describer;
	private Criterion criterion;

	private WebsiteWhen() {
		this.criterion = TriggerTemplates.getCritierionTemplate(this);
		this.describer = TriggerTemplates.getDescriber(this);
	}

	public String describe(final ParametersList params) {
		return describer.describe(params);
	}

	public boolean test(final ParametersList params, OrderInfo order) {
		return criterion.test(params, order);
	}
}
