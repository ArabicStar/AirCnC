package utils.condition;

import java.time.LocalDate;
import java.util.Set;

public class Condition {
	public enum Type {
		全部, 单人间, 双人间, 三人间, 其它;
	}

	private double rankGreaterThan = 0.0;
	private IntegerBound starBound = new IntegerBound();
	private DoubleBound priceBound = new DoubleBound();;
	private String scopeLike = "";
	private String nameLike = "";
	private Type roomTypes = Type.全部;
	private boolean available = false;
	private LocalDate since = LocalDate.now();

	public int getStarFrom() {
		return starBound.getFrom();
	}

	public int getStarTo() {
		return starBound.getTo();
	}

	public double getPriceFrom() {
		return priceBound.getFrom();
	}

	public double getPriceTo() {
		return priceBound.getTo();
	}

	public double getRankGreaterThan() {
		return rankGreaterThan;
	}

	/**
	 * @return the scopeLike
	 */
	public String getScopeLike() {
		return scopeLike;
	}

	/**
	 * @return the nameLike
	 */
	public String getNameLike() {
		return nameLike;
	}

	/**
	 * @return the roomTypes
	 */
	public Type getRoomTypes() {
		return roomTypes;
	}

	/**
	 * @return the avaliable
	 */
	public boolean isAvaliable() {
		return available;
	}

	/**
	 * @return the since
	 */
	public LocalDate getSince() {
		return since;
	}

	/**
	 * @param rankBound
	 *            the rankBound to set
	 */
	Condition setRankGreaterThan(double rankGreaterThan) {
		this.rankGreaterThan = rankGreaterThan;
		return this;
	}

	/**
	 * @param starBound
	 *            the starBound to set
	 */
	Condition setStarBound(IntegerBound starBound) {
		this.starBound = starBound;
		return this;
	}

	/**
	 * @param priceBound
	 *            the priceBound to set
	 */
	Condition setPriceBound(DoubleBound priceBound) {
		this.priceBound = priceBound;
		return this;
	}

	/**
	 * @param scopeLike
	 *            the scopeLike to set
	 */
	Condition setScopeLike(String scopeLike) {
		this.scopeLike = scopeLike;
		return this;
	}

	/**
	 * @param nameLike
	 *            the nameLike to set
	 */
	Condition setNameLike(String nameLike) {
		this.nameLike = nameLike;
		return this;
	}

	/**
	 * @param roomTypes
	 *            the roomTypes to set
	 */
	Condition setRoomTypes(Type roomTypes) {
		this.roomTypes = roomTypes;
		return this;
	}

	/**
	 * @param available
	 *            the available to set
	 */
	Condition setAvaliable(boolean available) {
		this.available = available;
		return this;
	}

	/**
	 * @param since
	 *            the since to set
	 */
	Condition setSince(LocalDate since) {
		this.since = since;
		return this;
	}
}
