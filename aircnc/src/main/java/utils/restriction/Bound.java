package utils.restriction;

public abstract class Bound<T extends Comparable<T>> {
	protected T from;
	protected T to;
	private boolean isInclusive;

	protected Bound(T from, T to, boolean isInclusive) {
		this.from = from;
		this.to = to;
		this.isInclusive = isInclusive;
	}

	protected Bound(T from, T to) {
		this(from, to, true);
	}

	public T getFrom() {
		return from;
	}

	public void setFrom(T from) {
		this.from = from;
	}

	public T getTo() {
		return to;
	}

	public void setTo(T to) {
		this.to = to;
	}

	public boolean isInclusive() {
		return isInclusive;
	}

	public void setInclusive(boolean isInclusive) {
		this.isInclusive = isInclusive;
	}

	public boolean inBound(T val) {
		if (val == null)
			throw new IllegalArgumentException("Null Comparable Object.");

		boolean flag1 = from == null ? true : isInclusive ? from.compareTo(val) <= 0 : from.compareTo(val) < 0;
		boolean flag2 = to == null ? true : isInclusive ? to.compareTo(val) >= 0 : to.compareTo(val) > 0;
		return flag1 && flag2;
	}
}
