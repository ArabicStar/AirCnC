package utils.info.member.credit;

public class ActionProperties {
	private int intVal1 = Integer.MIN_VALUE;
	private int intVal2 = Integer.MIN_VALUE;
	private double doubleVal1 = Double.NaN;
	private double doubleVal2 = Double.NaN;
	private String strVal1 = null;
	private String strVal2 = null;

	public ActionProperties(ActionProperties properties) {
		setIntVal1(properties.getIntVal1());
		setIntVal2(properties.getIntVal2());
		setDoubleVal1(properties.getDoubleVal1());
		setDoubleVal2(properties.getDoubleVal2());
		setStrVal1(properties.getStrVal1());
		setStrVal2(properties.getStrVal2());

	}

	public void setIntVal1(int intVal1) {
		this.intVal1 = intVal1;
	}

	public void setIntVal2(int intVal2) {
		this.intVal2 = intVal2;
	}

	public void setDoubleVal1(double doubleVal1) {
		this.doubleVal1 = doubleVal1;
	}

	public void setDoubleVal2(double doubleVal2) {
		this.doubleVal2 = doubleVal2;
	}

	public void setStrVal1(String strVal1) {
		this.strVal1 = strVal1;
	}

	public void setStrVal2(String strVal2) {
		this.strVal2 = strVal2;
	}

	public int getIntVal1() {
		return intVal1;
	}

	public int getIntVal2() {
		return intVal2;
	}

	public double getDoubleVal1() {
		return doubleVal1;
	}

	public double getDoubleVal2() {
		return doubleVal2;
	}

	public String getStrVal1() {
		return strVal1;
	}

	public String getStrVal2() {
		return strVal2;
	}
}
