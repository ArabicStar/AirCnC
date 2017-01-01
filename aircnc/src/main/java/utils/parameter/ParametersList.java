package utils.parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5577249141678283556L;
	private List<Parameter<?>> properties;

	public ParametersList() {
		this.properties = new ArrayList<>();
	}

	public ParametersList(ParametersList that) {
		this.properties = new ArrayList<>(that.properties);
	}

	private void put(Parameter<?> param) {
		properties.add(param);
	}

	public <T> void addParameter(String name, Class<T> type) {
		properties.add(new Parameter<>(name, type));
	}

//	public <T> void addParameter(String name, Class<T> type, ParameterCriteria<T> limit) {
//		properties.add(new Parameter<>(name, type, limit));
//	}

//	public <T> void addParameter(String name, Class<T> type, ParameterCriteria<T> limit, boolean notNull) {
//		properties.add(new Parameter<>(name, type, limit).setNotNull(notNull));
//	}

	public <T> void addParameter(String name, Class<T> type, boolean notNull) {
		properties.add(new Parameter<>(name, type).setNotNull(notNull));
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

		return p.putValue(this, value);
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Parameter<?> param : properties)
			sb.append("~").append(param.toString()).append("~");

		return sb.toString();
	}

	public static ParametersList parseString(String src) throws Exception {
		Pattern pt = Pattern.compile("~(.*?)~");
		Matcher m = pt.matcher(src);

		ParametersList list = new ParametersList();
		while (m.find())
			list.put(Parameter.parseString(m.group(1)));

		return list;
	}
}
