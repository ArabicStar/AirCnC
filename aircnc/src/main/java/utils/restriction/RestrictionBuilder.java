package utils.restriction;

public class RestrictionBuilder {
	private DoubleBound rankBound;
	private IntegerBound starBound;

	public RestrictionBuilder() {
		rankBound = new DoubleBound(0.0, 10.0);
		starBound = new IntegerBound(0, 5);
	}

	public void rankGreaterThan(double val) {
		rankBound.setFrom(val);
	}

	public void starBetween(int from, int to) {
		starBound.setFrom(from);
		starBound.setTo(to);
	}
}
