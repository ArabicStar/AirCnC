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

	public static HotelPromotionInfoManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionInfoManager();
	}

	public static HotelPromotionInfoManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private HotelPromotionInfoManager() {

	}

	private PromotionQueryDao dao;

	@Override
	public Set<PromotionVo> getUserAvailablePromotions(int hotelId) {
		return dao.getHotelAllPromotions(hotelId).stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.filter(vo -> vo.getPractical()).collect(Collectors.toSet());
	}

}
