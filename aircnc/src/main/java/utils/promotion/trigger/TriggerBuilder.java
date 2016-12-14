package utils.promotion.trigger;

import static utils.exception.StaticExceptionFactory.illegalArgEx;

import utils.promotion.trigger.hotel.HotelTrigger;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteTrigger;
import utils.promotion.trigger.website.WebsiteWhen;

import static utils.exception.StaticExceptionFactory.*;

import utils.property.ParametersList;

public class TriggerBuilder {
	private ParametersList parameters;
	private WebsiteWhen wwhen;
	private HotelWhen hwhen;

	public TriggerBuilder(WebsiteWhen when) {
		if (when == null)
			throw illegalArgEx("Trigger when");

		this.wwhen = when;
		parameters = TriggerTemplates.getParametersTemplate(when);
	}

	public TriggerBuilder(HotelWhen when) {
		if (when == null)
			throw illegalArgEx("Trigger when");

		this.hwhen = when;
		parameters = TriggerTemplates.getParametersTemplate(when);
	}

	public <T> TriggerBuilder setParam(TriggerParams paramName, T value) {
		parameters.putParameterValue(paramName.paramName(), value);
		return this;
	}

	public Trigger build() {
		if (wwhen != null && hwhen == null && parameters.isSetUp())
			return new WebsiteTrigger(wwhen, parameters);
		else if (wwhen == null && hwhen != null && parameters.isSetUp())
			return new HotelTrigger(hwhen, parameters);
		else
			throw illegalStateException("Not set up or ambigulous state");
	}
}
