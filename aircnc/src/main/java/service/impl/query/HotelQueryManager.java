package service.impl.query;

import static utils.exception.StaticExceptionFactory.*;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import data.dao.query.HotelQueryDao;
import po.hotel.HotelPo;
import service.query.HotelQueryService;
import utils.condition.Condition;
import utils.condition.ConditionBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;

public final class HotelQueryManager implements HotelQueryService {
	private static HotelQueryService instance;

	public static HotelQueryService launch(HotelQueryDao queryDao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelQueryManager(queryDao);
	}

	public static HotelQueryService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private HotelQueryDao queryDao;

	private HotelQueryManager(HotelQueryDao queryDao) {
		this.queryDao = queryDao;
	}

	@Override
	public HotelVo findById(int hotelId) {
		if (!HotelVo.checkID(hotelId))
			throw illegalArgEx("Hotel id");

		return new HotelVoBuilder(queryDao.searchById(hotelId)).getHotelInfo();
	}

	@Override
	public HotelVo findByName(String name) {
		if (!HotelVo.checkHotelName(name))
			throw illegalArgEx("Hotel name");

		return new HotelVoBuilder(queryDao.searchByName(name)).getHotelInfo();
	}

	@Override
	public List<HotelVo> findByCondition(Condition cond) {
		DetachedCriteria dc = ConditionBuilder.parseCondition(DetachedCriteria.forClass(HotelPo.class), cond);
		return queryDao.searchByCriteria(dc).stream().map(h -> new HotelVoBuilder(h).getHotelInfo())
				.collect(Collectors.toList());
	}

}
