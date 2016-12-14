package utils.promotion;

import utils.parameter.ParametersList;

@FunctionalInterface
public interface Describer {
	public String describe(ParametersList params);
}
