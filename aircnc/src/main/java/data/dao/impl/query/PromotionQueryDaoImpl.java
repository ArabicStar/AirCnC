package data.dao.impl.query;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import data.dao.query.PromotionQueryDao;
import po.promotion.HotelPromotionPo;
import po.promotion.PromotionPo;
import po.promotion.WebsitePromotionPo;
import utils.info.hotel.HotelInfoTemplate;

public enum PromotionQueryDaoImpl implements PromotionQueryDao {
	INSTANCE;

	@Override
	public Set<PromotionPo> getHotelAllPromotions(int hotelId) {
		if (!HotelInfoTemplate.checkID(hotelId))
			throw illegalArgEx("Hotel id", hotelId);

		return execute(session -> {
			Criteria c = session.createCriteria(HotelPromotionPo.class);
			c.add(Restrictions.eq("hotelId", hotelId));

			@SuppressWarnings("unchecked")
			List<HotelPromotionPo> list = (List<HotelPromotionPo>) c.list();

			return new HashSet<>(list);
		});
	}

	@Override
	public Set<PromotionPo> getWebsiteAllPromotions() {
		return execute(session -> {
			String hql = "from WebsitePromotionPo";

			@SuppressWarnings("unchecked")
			List<WebsitePromotionPo> list = (List<WebsitePromotionPo>) session.createQuery(hql).list();

			return new HashSet<>(list);
		});
	}

	@Override
	public PromotionPo getHotelPromotion(long id) {
		return execute(session -> session.get(HotelPromotionPo.class, id));
	}

	@Override
	public PromotionPo getWebsitePromotion(long id) {
		return execute(session -> session.get(WebsitePromotionPo.class, id));
	}

}
