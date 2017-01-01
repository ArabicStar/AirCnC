package utils.promotion.trigger;

import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;

import org.apache.commons.lang.StringUtils;

import utils.parameter.ParametersList;
import utils.promotion.trigger.hotel.HotelTrigger;
import utils.promotion.trigger.hotel.HotelWhen;
import utils.promotion.trigger.website.WebsiteTrigger;
import utils.promotion.trigger.website.WebsiteWhen;

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
		if (parameters.putParameterValue(paramName.paramName(), value))
			return this;

		throw illegalArgEx("Promotion trigger parameter: " + paramName + ": " + value);
	}

	public boolean isReady() {
		return parameters.isSetUp() && (wwhen == null ^ hwhen == null);
	}

	public Trigger build() {
		if (!isReady())
			throw illegalStateException("Not set up or ambigulous state");

		if (wwhen != null && hwhen == null && parameters.isSetUp())
			return new WebsiteTrigger(wwhen, parameters);
		else if (wwhen == null && hwhen != null && parameters.isSetUp())
			return new HotelTrigger(hwhen, parameters);

		return null;// never reach, only make complier happy
	}

	public static final Trigger parseString(String src) throws Exception {
		String[] strs = StringUtils.split(src, "@^@");// Don't worry, be happy

		if (strs.length == 0)
			throw illegalArgEx("Trigger source string");

		ParametersList list = new ParametersList();
		if (strs.length == 3)
			list = ParametersList.parseString(strs[2]);

		switch (strs[0]) {
		case "Website":
			return new WebsiteTrigger(WebsiteWhen.valueOf(strs[1]), list);
		case "Hotel":
			return new HotelTrigger(HotelWhen.valueOf(strs[1]), list);
		default:
			throw illegalArgEx("Trigger source string");
		}
	}
}
