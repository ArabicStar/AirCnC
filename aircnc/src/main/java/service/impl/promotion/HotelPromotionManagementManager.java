package service.impl.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;
import java.util.stream.Collectors;

import data.dao.promotion.HotelPromotionDao;
import data.dao.query.PromotionQueryDao;
import po.promotion.HotelPromotionPo;
import po.promotion.PromotionPoBuilder;
import service.promotion.HotelPromotionInfoService;
import service.promotion.HotelPromotionManagmentService;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class HotelPromotionManagementManager implements HotelPromotionManagmentService, HotelPromotionInfoService {
	/* Singleton */
	private static HotelPromotionManagementManager instance;

	public static HotelPromotionManagementManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelPromotionManagementManager();
	}

	public static HotelPromotionManagementManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private HotelPromotionManagementManager() {

	}

	private PromotionQueryDao query;
	private HotelPromotionDao dao;

	@Override
	public boolean addPromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.addHotelPromotion((HotelPromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public boolean deletePromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.deleteHotelPromotion(vo.getId());
	}

	@Override
	public boolean updatePromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.updateHotelPromotion((HotelPromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public Set<PromotionVo> getAllPromotions(int hotelId) {
		return query.getHotelAllPromotions(hotelId).stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<PromotionVo> getActivePromotion(int hotelId) {
		return getAllPromotions(hotelId).stream().filter(po -> po.getActive()).collect(Collectors.toSet());
	}

	@Override
	public Set<PromotionVo> getUserAvailablePromotions(int hotelId) {
		return getAllPromotions(hotelId).stream().filter(po -> po.getPractical()).collect(Collectors.toSet());
	}
}
