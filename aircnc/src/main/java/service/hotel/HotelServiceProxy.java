package service.hotel;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public final class HotelServiceProxy extends AccessSecureProxy
		implements HotelAccountService, HotelInfoService, HotelOrderService {

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

	private HotelServiceProxy(Client clientId) {
		super(clientId);
	}

	private HotelInfoService infoService;

	@AuthenticatePolicy({ Client.HOTEL, Client.USER, Client.MANAGE })
	public void loadInfoService(HotelInfoService infoService) {
		checkAuthentication();

		this.infoService = infoService;
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER, Client.MANAGE })
	public HotelInfo getHotelInfo(String name) {
		checkAuthentication();

		return infoService.getHotelInfo(name);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER })
	public List<CommentVo> getHotelComment(int id) {
		checkAuthentication();

		return infoService.getHotelComment(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.USER })
	public Set<PromotionVo> getHotelActivePromotion(int id) {
		checkAuthentication();

		return infoService.getHotelActivePromotion(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public boolean updateInfo(HotelInfo modifiedInfo) {
		checkAuthentication();

		return infoService.updateInfo(modifiedInfo);
	}
	

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean updatePassword(int oldPass, int newPass) {
		checkAuthentication();
		
		return infoService.updatePassword(oldPass, newPass);
	}

	private HotelAccountService accountService;

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

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public HotelInfo register(HotelVoBuilder newHotel, int passwordHash) {
		checkAuthentication();

		return accountService.register(newHotel, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo login(String name, int passwordHash) {
		checkAuthentication();

		return accountService.login(name, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean logout() {
		checkAuthentication();

		return accountService.logout();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean isLogined() {
		checkAuthentication();

		return accountService.isLogined();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo refreshCurrentAccount() {
		checkAuthentication();

		return accountService.refreshCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public HotelInfo getCurrentAccount() {
		checkAuthentication();

		return accountService.getCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.MANAGE })
	public boolean existsHotel(String name) {
		checkAuthentication();

		return accountService.existsHotel(name);
	}

	/*
	 **********************************
	 ******* HotelOrderService*******
	 **********************************
	 */
	private HotelOrderService orderService;

	@AuthenticatePolicy({ Client.HOTEL })
	public void loadHotelOrderService(HotelOrderService hotelOrderService) {
		checkAuthentication();

		this.orderService = hotelOrderService;
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public List<OrderVo> getHotelAllOrders(int id) {
		checkAuthentication();

		return orderService.getHotelAllOrders(id);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public List<OrderVo> getHotelOrdersByStatus(int id, OrderStatus status) {
		checkAuthentication();

		return orderService.getHotelOrdersByStatus(id, status);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean executeOrder(OrderInfo order) {
		checkAuthentication();

		return orderService.executeOrder(order);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public boolean appealOrder(OrderInfo order) {
		checkAuthentication();

		return orderService.appealOrder(order);
	}


}
