package utils.promotion;

import java.io.Serializable;

import utils.parameter.ParametersList;

@FunctionalInterface
public interface Describer extends Serializable{
	public String describe(ParametersList params);
}
