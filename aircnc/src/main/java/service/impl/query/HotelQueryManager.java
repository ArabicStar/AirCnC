package service.impl.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

		HotelPo po = queryDao.searchById(hotelId);
		if (po == null)
			return null;

		return new HotelVoBuilder(queryDao.searchById(hotelId)).getHotelInfo();
	}

	@Override
	public HotelVo findByName(String name) {
		if (!HotelVo.checkHotelName(name))
			throw illegalArgEx("Hotel name");

		HotelPo po = queryDao.searchByName(name);
		if (po == null)
			return null;

		return new HotelVoBuilder(queryDao.searchByName(name)).getHotelInfo();
	}
	
	private String bufferScope = "";
	private List<HotelVo> bufferHotels = null ;

	@Override
	public List<HotelVo> findByCondition(Condition cond) {
		if (cond == null)
			return null;
		if(cond.getScopeLike().length()>0&&findByScope(cond.getScopeLike())){
			return bufferHotels;
		}
		
		if(cond.getNameLike().length()>0){
			List<HotelVo> result = new ArrayList<HotelVo>();
			result.add(findByName(cond.getNameLike()));
			return result;
		}
		
		if(bufferHotels==null||bufferHotels.isEmpty()){
			return null;
		}
		
		//酒店最低价要低于价格区间的高价位,或者最高价要高于低价位
		return bufferHotels.stream().filter(h->h.getLowestPrice()<=cond.getPriceTo()||h.getHighestPrice()>=cond.getPriceFrom()).
				filter(h->h.getGrade()>=cond.getRankGreaterThan()).
				filter(h->h.getStar()>=cond.getStarFrom()).
				filter(h->h.getStar()<=cond.getStarTo()).
				filter(h->h.hasRoom(cond.getRoomTypes(), cond.isAvaliable())).
				collect(Collectors.toList());
		
		
	}
	
	private boolean findByScope(String scope){
		if(scope.equals(bufferScope)){
			return true;
		}
		bufferHotels = queryDao.searchByCriteria(ConditionBuilder.parseCondition(scope)).stream().
				map(h -> new HotelVoBuilder(h).getHotelInfo()).collect(Collectors.toList());
		return true;
	}

}
