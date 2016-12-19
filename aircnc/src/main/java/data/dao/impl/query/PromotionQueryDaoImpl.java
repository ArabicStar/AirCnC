package data.dao.impl.query;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

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

public class PromotionQueryDaoImpl implements PromotionQueryDao {
	/* Singleton */
	private static PromotionQueryDaoImpl instance;

	public static PromotionQueryDaoImpl launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new PromotionQueryDaoImpl();
	}

	public static PromotionQueryDaoImpl getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private PromotionQueryDaoImpl() {
	}

	@Override
	public Set<PromotionPo> getHotelAllPromotions(int hotelId) {
		if (!HotelInfoTemplate.checkID(hotelId))
			throw illegalArgEx("Hotel id: " + hotelId);

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
			String hql = "from PromotionPo";

			@SuppressWarnings("unchecked")
			List<WebsitePromotionPo> list = (List<WebsitePromotionPo>) session.createQuery(hql).list();

			return new HashSet<>(list);
		});
	}

}
