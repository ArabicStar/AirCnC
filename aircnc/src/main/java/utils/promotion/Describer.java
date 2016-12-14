package utils.promotion;

import utils.property.ParametersList;

@FunctionalInterface
public interface Describer {
	public String describe(ParametersList params);
}
