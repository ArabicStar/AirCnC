package utils.condition;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import po.hotel.HotelPo;
import utils.condition.Condition.Type;

public class ConditionBuilder {
	private double rankGreaterThan;
	private IntegerBound starBound;
	private DoubleBound priceBound;
	private String scopeLike;
	private String nameLike;
	private Type roomTypes;
	private boolean available;
	private LocalDate since;

	public ConditionBuilder() {
		rankGreaterThan = 0.0;
		starBound = new IntegerBound(0, 5);
		priceBound = new DoubleBound(0.0,1000.0);
		scopeLike = "";
		nameLike = "";
		roomTypes = Type.全部;
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
	
	public void priceBetween(double from, double to) {
		priceBound.from(from);
		priceBound.to(to);
	}

	public void scopeLike(String scopeLike) {
		this.scopeLike = scopeLike;
	}

	public void nameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public void roomNeed(Type roomType) {
		this.roomTypes = roomType;
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

	public static DetachedCriteria parseCondition(final String scope) {
		// TODO
//		criteria.add(Restrictions.like("name", cond.getNameLike())).add(Restrictions.like("scope", cond.getScopeLike()))
//				.add(Restrictions.ge("grade", cond.getRankGreaterThan()))
//				.add(Restrictions.between("star", cond.getStarFrom(), cond.getStarTo()));
		DetachedCriteria criteria = DetachedCriteria.forClass(HotelPo.class);
		criteria.add(Restrictions.like("scope", scope));
		return criteria;
	}
}
