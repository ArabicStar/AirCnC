package utils.condition;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import utils.info.hotel.RoomInfoTemplate.Type;

public class ConditionBuilder {
	private double rankGreaterThan;
	private IntegerBound starBound;
	private String scopeLike;
	private String nameLike;
	private Set<Type> roomTypes;
	private boolean available;
	private LocalDate since;

	public ConditionBuilder() {
		rankGreaterThan = 0.0;
		starBound = new IntegerBound(0, 5);
		scopeLike = "";
		nameLike = "";
		roomTypes = new HashSet<>();
		available = false;
		since = LocalDate.now().plusDays(1);
	}

	public void rankGreaterThan(double val) {
		rankGreaterThan = val;
	}

	public void starBetween(int from, int to) {
		starBound.from(from);
		starBound.to(to);
	}

	public void scopeLike(String scopeLike) {
		this.scopeLike = scopeLike;
	}

	public void nameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void roomNeed(Type roomType) {
		this.roomTypes.add(roomType);
	}

	public void roomNeed(Set<Type> roomTypes) {
		this.roomTypes.addAll(roomTypes);
	}

	public void avaliable(boolean available) {
		this.available = available;
	}

	public void since(LocalDate since) {
		this.since = since;
	}

	public Condition buildCondition() {
		return new Condition().setAvaliable(available).setNameLike(nameLike).setRankGreaterThan(rankGreaterThan)
				.setRoomTypes(roomTypes).setScopeLike(scopeLike).setSince(since).setStarBound(starBound);
	}

	public static DetachedCriteria parseCondition(DetachedCriteria criteria, final Condition cond) {
		// TODO
		criteria.add(Restrictions.like("NAME", cond.getNameLike())).add(Restrictions.like("SCOPE", cond.getScopeLike()))
				.add(Restrictions.ge("GRADE", cond.getRankGreaterThan()))
				.add(Restrictions.between("STAR", cond.getStarFrom(), cond.getStarTo()));
		return criteria;
	}
}
