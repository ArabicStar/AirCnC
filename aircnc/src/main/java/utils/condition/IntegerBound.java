package utils.condition;

public class IntegerBound extends Bound<Integer> {

	public IntegerBound() {
		super(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	protected IntegerBound(Integer from, Integer to) {
		super(from, to);

	}

}
