package service.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;

import utils.condition.Condition;
import utils.info.hotel.HotelInfo;
import utils.info.order.OrderStatus;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public final class HotelServiceProxy extends AccessSecureProxy 
	implements HotelAccountService,HotelInfoService{

	private static HotelServiceProxy instance;

	public static HotelServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new HotelServiceProxy(clientId);
	}

	public static HotelServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	private HotelAccountService accountService;
	private HotelInfoService infoService;

	private HotelServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 ***************************
	 * Actual manager loader
	 ***************************
	 */
	@AuthenticatePolicy({ Client.HOTEL })
	public void loadAccountService(HotelAccountService accountService) {
		checkAuthentication();

		this.accountService = accountService;
	}

	@AuthenticatePolicy({ Client.HOTEL, Client.USER, Client.MANAGE })
	public void loadInfoService(HotelInfoService infoService) {
		checkAuthentication();

		this.infoService = infoService;
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER, Client.MANAGE })
	public HotelInfo getHotelInfo(String name) {

		return infoService.getHotelInfo(name);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL})
	public List<OrderVo> getHotelAllOrders(int id) {

		return infoService.getHotelAllOrders(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL})
	public List<OrderVo> getHotelOrdersByStatus(int id, OrderStatus status) {

		return infoService.getHotelOrdersByStatus(id, status);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER})
	public List<CommentVo> getHotelComment(int id) {

		return infoService.getHotelComment(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER})
	public Set<PromotionVo> getHotelActivePromotion(int id) {
		
		return infoService.getHotelActivePromotion(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL})
	public Set<PromotionVo> getHotelAllPromotions(int hotelId) {

		return infoService.getHotelAllPromotions(hotelId);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL,Client.MANAGE })
	public boolean updateInfo(HotelInfo modifiedInfo) {

		return infoService.updateInfo(modifiedInfo);
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public HotelInfo register(HotelVoBuilder newHotel, int passwordHash) {
		return accountService.register(newHotel, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo login(String name, int passwordHash) {
		return accountService.login(name, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean logout() {
		return accountService.logout();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean isLogined() {
		return accountService.isLogined();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo refreshCurrentAccount() {
		return accountService.refreshCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo getCurrentAccount() {
		return accountService.getCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean existsHotel(String name) {
		return accountService.existsHotel(name);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public List<HotelVo> findByCondition(Condition cond) {
		return infoService.findByCondition(cond);
	}
	
}
