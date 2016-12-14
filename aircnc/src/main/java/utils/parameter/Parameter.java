package utils.parameter;

import static utils.exception.StaticExceptionFactory.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Predicate;

public class Parameter<T> {
	private String name;
	private Class<T> type;
	private T value;
	private Predicate<T> limit;

	private Parameter(String name) {
		if (name == null)
			illegalArgEx("Property name");

		this.name = name;
		this.value = null;
	}

	public Parameter(String name, Class<T> type) {
		this(name);
		this.type = type;
	}

	public Parameter(String name, Class<T> type, Predicate<T> precondition) {
		this(name, type);
		this.limit = precondition;
	}

	@SuppressWarnings("unchecked")
	public Parameter(String name, T value) {
		this(name);
		this.value = value;
		ParameterizedType p = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = p.getActualTypeArguments();
		type = (Class<T>) types[0];
	}

	public Parameter(String name, T value, Predicate<T> limit) {
		this(name, value);
		this.limit = limit;
	}

	public T value() {
		return value;
	}

	public Class<T> type() {
		return type;
	}

	public String name() {
		return name;
	}

	public boolean putValue(T value) {
		if (!limit.test(value))
			return false;

		this.value = value;
		return true;
	}

	public boolean nameEquals(Parameter<T> that) {
		return this.name().equals(that.name());
	}

	public boolean contentEqual(Parameter<T> that) {
		if (this.value() == null)
			if (that.value == null)
				return true;
			else
				return false;

		return this.value().equals(that.value);
	}

	public boolean typeMatches(Class<?> thatClass) {
		return type.isAssignableFrom(thatClass);
	}

	public boolean typeMatches(Parameter<?> thatProperty) {
		return typeMatches(thatProperty.getClass());
	}
}
