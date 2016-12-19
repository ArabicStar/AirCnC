package utils.parameter;

import java.io.Serializable;

public interface ParameterCriteria<T> extends Serializable {
	public boolean test(ParametersList list, T value);
}
