package utils.promotion.applier;

import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;

import org.apache.commons.lang.StringUtils;

import utils.parameter.ParametersList;

public class ApplierBuilder {
	private ParametersList parameters;
	private How how;

	public ApplierBuilder(How how) {
		if (how == null)
			throw illegalArgEx("Applierwhen");

		this.how = how;
		parameters = ApplierTemplates.getParametersTemplate(how);
	}

	public <T> ApplierBuilder setParam(ApplierParams paramName, T value) {
		parameters.putParameterValue(paramName.paramName(), value);
		return this;
	}

	public boolean isReady() {
		return how != null;
	}

	public Applier build() {
		if (!isReady())
			throw illegalStateException("Not set up");

		return new Applier(parameters, how);
	}

	public static final Applier parseString(String src) throws Exception {
		String[] strs = StringUtils.split(src, "@^@");

		if (strs.length != 2)
			throw illegalArgEx("Applier source string");

		How how = How.valueOf(strs[0]);
		ParametersList list = ParametersList.parseString(strs[1]);

		return new Applier(list, how);
	}
}
