package utils.promotion.applier;

import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;

import utils.property.ParametersList;

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

	public Applier build() {
		if (how == null)
			throw illegalStateException("Not set up");

		return new Applier(parameters, how);
	}
}
