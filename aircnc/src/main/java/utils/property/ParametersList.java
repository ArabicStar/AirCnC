package utils.property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ParametersList {
	private List<Parameter<?>> properties;

	public ParametersList() {
		this.properties = new ArrayList<>();
	}

	public ParametersList(ParametersList that) {
		this.properties = new ArrayList<>(that.properties);
	}

	public <T> void addParameter(String name, Class<T> type) {
		properties.add(new Parameter<>(name, type));
	}

	public <T> void addParameter(String name, Class<T> type, Predicate<T> limit) {
		properties.add(new Parameter<>(name, type, limit));
	}

	public <T> T getParameterValue(String name) {
		Parameter<T> p = getParameter(name);
		if (p == null)
			return null;
		return p.type().cast(p.value());
	}

	public <T> boolean putParameterValue(String name, T value) {
		Parameter<T> p = getParameter(name);
		if (p == null)
			return false;

		return p.putValue(value);
	}

	public <T> boolean existParameter(String name) {
		for (Parameter<?> p : properties)
			if (p.name().equals(name))
				return true;

		return false;
	}

	public boolean isSetUp() {
		for (Parameter<?> p : properties)
			if (p.value() == null)
				return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	public <T> Parameter<T> getParameter(String name) {
		for (Parameter<?> p : properties)
			if (p.name().equals(name))
				return (Parameter<T>) p;

		return null;
	}
}
