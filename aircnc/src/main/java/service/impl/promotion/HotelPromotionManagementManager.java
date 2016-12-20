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
import service.promotion.HotelPromotionManagementService;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.PromotionVoBuilder;

public class HotelPromotionManagementManager implements HotelPromotionManagementService {
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
	public boolean addHotelPromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.addHotelPromotion((HotelPromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public boolean deleteHotelPromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.deleteHotelPromotion(vo.getId());
	}

	@Override
	public boolean updateHotelPromotion(HotelPromotionVo vo) {
		if (vo == null || !vo.isValid())
			throw illegalArgEx("Hotel promotion vo");

		return dao.updateHotelPromotion((HotelPromotionPo) new PromotionPoBuilder(vo).getPromotionInfo());
	}

	@Override
	public Set<PromotionVo> getHotelAllPromotions(int hotelId) {
		return query.getHotelAllPromotions(hotelId).stream().map(po -> new PromotionVoBuilder(po).getPromotionInfo())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<PromotionVo> getHotelActivePromotion(int hotelId) {
		return getHotelAllPromotions(hotelId).stream().filter(po -> po.getActive()).collect(Collectors.toSet());
	}
}
