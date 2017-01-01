package utils.info.level;

import java.io.Serializable;

import utils.parameter.ParametersList;

public class LevelStrategy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2404867525093676340L;
	private int id = 1;
	private ParametersList list;

	public LevelStrategy() {
		list = new ParametersList();
		initList();
	}

	public void initList() {
		list.addParameter("0", Integer.class);
		list.putParameterValue("0", 0);
		for (int i = 1; i <= 10; i++)
			list.addParameter(String.valueOf(i), Integer.class);
	}

	public int calcLevel(int credit) {
		for (int i = 10; i >= 0; i--)
			if ((int) list.getParameterValue(String.valueOf(i)) <= credit)
				return i;

		return 0;
	}

	public void setThreshold(int level, int threshold) {
		if (level < 0 || level > 10 || threshold < 0)
			return;

		list.putParameterValue(String.valueOf(level), threshold);
	}

	public int getTreshold(int level) {
		if (level <= 0 || level >= 10)
			throw new IllegalArgumentException("LevelStrategy - Level Illegal");
		return list.getParameterValue(String.valueOf(level));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
	}

	public String getContentString() {
		return list.toString();
	}

	public void setContentString(String contentString) throws Exception {
		list = ParametersList.parseString(contentString);
	}
}
