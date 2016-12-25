package utils.condition;

public class DoubleBound extends Bound<Double> {

	public DoubleBound() {
		super(-Double.MAX_VALUE, Double.MAX_VALUE);
	}

	protected DoubleBound(Double from, Double to) {
		super(from, to);

	}

}
