package interactor.impl.member;

import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;

import interactor.member.HotelSearchInteractor;
import interactor.utils.Title;
import presentation.manage.accessor.HotelManageInfoAccessor;
import presentation.manage.accessor.impl.HotelManageInfoAccessorImpl;
import presentation.manage.manager.HotelManageInfoManager;
import presentation.manage.manager.impl.HotelManageInfoManagerImpl;
import presentation.member.accessor.HotelSearchAccessor;
import presentation.member.accessor.impl.HotelSearchAccessorImpl;
import presentation.member.manager.SearchHotelManager;
import presentation.member.manager.impl.SearchHotelManagerImpl;
import service.query.HotelQueryService;
import vo.hotel.HotelVo;

public class HotelSearchCourier implements HotelSearchInteractor {
	private static HotelSearchInteractor instance;

	public static HotelSearchInteractor launch(HotelQueryService handler) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelSearchCourier(handler);
	}

	public static HotelSearchInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private HotelQueryService handler;

	private HotelSearchCourier(HotelQueryService handler) {
		this.handler = handler;
	}
	
	@Override
	@Title("Search Hotel By Id")
	public boolean searchById() {
		String title = getTitle();

		HotelManageInfoAccessor acs = HotelManageInfoAccessorImpl.getInstance();
		HotelVo hotel = execute(title, () -> {
			return handler.findById(Integer.parseInt(acs.getHotelId()));

		});
		if(hotel != null){
			HotelManageInfoManager man = HotelManageInfoManagerImpl.getInstance();
			man.setHotel(hotel);
			return true;
		}
		return false;
	}

	@Override
	@Title("Search Hotel By Condition")
	public boolean searchByCondition() {
		String title = getTitle();
		HotelSearchAccessor acs = HotelSearchAccessorImpl.getInstance();
		List<HotelVo> hotels = execute(title, () -> {
			return handler.findByCondition(acs.getCondition());

		});
		if(hotels != null){
			SearchHotelManager man = SearchHotelManagerImpl.getInstance();
			man.setHotel(hotels);
			return true;
		}
		return false;
	}

}
