package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import data.dao.query.PromotionQueryDao;
import service.promotion.HotelPromotionInfoService;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class HotelPromotionInfoManager implements HotelPromotionInfoService {
	/* Singleton */
	private static HotelPromotionInfoManager instance;

	public static HotelPromotionInfoManager launch(PromotionQueryDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionInfoManager(dao);
	}

	public static HotelPromotionInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private PromotionQueryDao dao;

	/**
	 * @param dao
	 */
	private HotelPromotionInfoManager(PromotionQueryDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Set<PromotionVo> getUserAvailableHotelPromotions(int hotelId) {
		return dao.getHotelAllPromotions(hotelId).stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.filter(vo -> vo.getPractical()).collect(Collectors.toSet());
	}

	@Override
	public PromotionVo getHotelPromotion(long id) {
		return new PromotionVoBuilder(dao.getHotelPromotion(id)).getPromotionInfo();
	}

}
