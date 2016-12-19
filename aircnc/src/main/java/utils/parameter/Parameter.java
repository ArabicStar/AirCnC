package utils.parameter;

import static utils.crypto.BytesCharsConverter.bytes2HexString;
import static utils.crypto.BytesCharsConverter.hexString2Bytes;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parameter<T> {
	private String name;
	private Class<T> type;
	private T value;
	private ParameterCriteria<T> limit;
	private boolean allowNull;

	private Parameter(String name) {
		if (name == null)
			illegalArgEx("Property name");

		this.name = name;
		this.limit = (list, o) -> true;
		this.value = null;
		this.allowNull = true;
	}

	public Parameter(String name, Class<T> type) {
		this(name);
		this.type = type;
	}

	public Parameter(String name, Class<T> type, ParameterCriteria<T> precondition) {
		this(name, type);
		setLimit(precondition);
	}

	@SuppressWarnings("unchecked")
	public Parameter(String name, T value) {
		this(name);
		this.value = value;
		ParameterizedType p = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = p.getActualTypeArguments();
		type = (Class<T>) types[0];
	}

	public Parameter(String name, T value, ParameterCriteria<T> limit) {
		this(name, value);
		setLimit(limit);
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

	private void putValue(T value) {
		this.value = value;
	}

	public boolean putValue(ParametersList list, T value) {
		if (value == null && allowNull) {
			this.value = null;
			return true;
		}

		if (!limit.test(list, value))
			return false;

		this.value = value;
		return true;
	}

	public Parameter<T> setNotNull(boolean notNull) {
		this.allowNull = !notNull;
		return this;
	}

	public boolean getNotNull() {
		return !allowNull;
	}

	private void setLimit(ParameterCriteria<T> limit) {
		if (limit != null)
			this.limit = limit;
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

	@Override
	public String toString() {
		// basic info
		StringBuilder sb = new StringBuilder("'").append(name).append("''").append(type.getName()).append("''");

		// serialize value
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] b = baos.toByteArray();
		sb.append(bytes2HexString(b)).append("''");
		// serialize value complete

		baos.reset();

		// serialize limit
		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(limit);
		} catch (IOException e) {
			e.printStackTrace();
		}

		b = baos.toByteArray();
		try {
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// serialize limit complete

		return sb.append(bytes2HexString(b)).append("'").toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Parameter<?> parseString(String src) throws Exception {
		Pattern pt = Pattern.compile("'(.+?)'");
		Matcher m = pt.matcher(src);

		// name
		m.find();
		String name = m.group(1);

		// type
		m.find();
		Class clazz = Class.forName(m.group(1));

		Parameter param = new Parameter<>(name, clazz);

		// value
		m.find();
		byte[] bs = hexString2Bytes(m.group(1));
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs))) {
			param.putValue(ois.readObject());
		} catch (Exception e) {
			throw e;
		}

		// limit
		m.find();
		bs = hexString2Bytes(m.group(1));
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs))) {
			param.setLimit((ParameterCriteria) ois.readObject());
		} catch (Exception e) {
			throw e;
		}
		return param;
	}
}
