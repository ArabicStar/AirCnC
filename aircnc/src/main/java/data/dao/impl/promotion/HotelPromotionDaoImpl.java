package data.dao.impl.promotion;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import data.dao.promotion.HotelPromotionDao;
import po.promotion.HotelPromotionPo;

public enum HotelPromotionDaoImpl implements HotelPromotionDao {
	INSTANCE;

	@Override
	public boolean addHotelPromotion(HotelPromotionPo po) {
		if (po == null)
			return false;
		if (po.getId() != 0)
			return false;

		return execute(session -> {
			session.save(po);

			return Boolean.TRUE;
		});
	}

	@Override
	public boolean deleteHotelPromotion(long id) {
		if (id <= 0)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			HotelPromotionPo toBeDel = session.get(HotelPromotionPo.class, id);

			if (flag = Boolean.valueOf(toBeDel != null))
				toBeDel.expire();

			return flag;
		});
	}

	@Override
	public boolean updateHotelPromotion(HotelPromotionPo po) {
		if (po == null || po.getId() <= 0)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			HotelPromotionPo toBeUpdate = session.get(HotelPromotionPo.class, po.getId());

			if (flag = Boolean.valueOf(toBeUpdate != null)) {
				po.setId(0);

				// save updated po
				session.save(po);

				// "delete" old po: set it inactive
				toBeUpdate.expire();
			}

			return flag;
		});

	}

	@Override
	public HotelPromotionPo findHotelPromotion(long id) {
		if (id <= 0)
			return null;

		return execute(session -> {
			return (HotelPromotionPo) session.get(HotelPromotionPo.class, id);
		});
	}

}
