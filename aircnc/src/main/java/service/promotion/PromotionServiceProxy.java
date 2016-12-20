package service.promotion;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import utils.info.order.OrderInfo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.promotion.HotelPromotionVo;
import vo.promotion.PromotionVo;
import vo.promotion.WebsitePromotionVo;

public class PromotionServiceProxy extends AccessSecureProxy
		implements PromotionApplicationService, WebsitePromotionManagementService, HotelPromotionManagementService,
		WebsitePromotionInfoService, HotelPromotionInfoService {

	/* Singleton */
	private static PromotionServiceProxy instance;

	public static PromotionServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new PromotionServiceProxy(clientId);
	}

	public static PromotionServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private PromotionServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 *******************************************
	 ******* HotelPromotionInfoService*******
	 *******************************************
	 */
	private HotelPromotionInfoService hotelInfo;

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.MANAGE })
	public void loadHotelPromotionInfoService(HotelPromotionInfoService hotelPromotionInfoService) {
		checkAuthentication();

		this.hotelInfo = hotelPromotionInfoService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.MANAGE })
	public Set<PromotionVo> getUserAvailableHotelPromotions(int hotelId) {
		checkAuthentication();

		return hotelInfo.getUserAvailableHotelPromotions(hotelId);
	}

	@Override
	public PromotionVo getWebsitePromotion(long id) {
		checkAuthentication();

		return websiteInfo.getWebsitePromotion(id);
	}

	/*
	 **********************************************
	 ******* WebsitePromotionInfoService*******
	 **********************************************
	 */
	private WebsitePromotionInfoService websiteInfo;

	public void loadWebsitePromotionInfoService(WebsitePromotionInfoService websitePromotionInfoService) {
		checkAuthentication();

		this.websiteInfo = websitePromotionInfoService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET, Client.MANAGE })
	public Set<PromotionVo> getUserAvailableWebsitePromotions() {
		checkAuthentication();

		return websiteInfo.getUserAvailableWebsitePromotions();
	}

	@Override
	public PromotionVo getHotelPromotion(long id) {
		checkAuthentication();

		return hotelInfo.getHotelPromotion(id);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public void refreshBuffer() {
		checkAuthentication();

		websiteInfo.refreshBuffer();
	}

	/*
	 *****************************************************
	 ******* HotelPromotionManagementService*******
	 *****************************************************
	 */
	private HotelPromotionManagementService hotelManage;

	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public void loadHotelPromotionManagementService(HotelPromotionManagementService hotelPromotionManagementService) {
		checkAuthentication();

		this.hotelManage = hotelPromotionManagementService;
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public boolean addHotelPromotion(HotelPromotionVo vo) {
		checkAuthentication();

		return hotelManage.addHotelPromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public boolean deleteHotelPromotion(HotelPromotionVo vo) {
		checkAuthentication();

		return hotelManage.deleteHotelPromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public boolean updateHotelPromotion(HotelPromotionVo vo) {
		checkAuthentication();

		return hotelManage.updateHotelPromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public Set<PromotionVo> getHotelAllPromotions(int hotelId) {
		checkAuthentication();

		return hotelManage.getHotelActivePromotion(hotelId);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.MANAGE })
	public Set<PromotionVo> getHotelActivePromotion(int hotelId) {
		checkAuthentication();

		return hotelManage.getHotelActivePromotion(hotelId);
	}

	/*
	 ********************************************************
	 ******* WebsitePromotionManagementService*******
	 ********************************************************
	 */
	private WebsitePromotionManagementService websiteManage;

	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public void loadWebsitePromotionManagementService(
			WebsitePromotionManagementService websitePromotionManagementService) {
		checkAuthentication();

		this.websiteManage = websitePromotionManagementService;
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public boolean addWebsitePromotion(WebsitePromotionVo vo) {
		checkAuthentication();

		return websiteManage.addWebsitePromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public boolean deleteWebsitePromotion(WebsitePromotionVo vo) {
		checkAuthentication();

		return websiteManage.deleteWebsitePromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public boolean updateWebsitePromotion(WebsitePromotionVo vo) {
		checkAuthentication();

		return websiteManage.updateWebsitePromotion(vo);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public Set<PromotionVo> getWebsiteAllPromotions() {
		checkAuthentication();

		return websiteManage.getWebsiteAllPromotions();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET, Client.MANAGE })
	public Set<PromotionVo> getWebsiteActivePromotion() {
		checkAuthentication();

		return websiteManage.getWebsiteActivePromotion();
	}

	/*
	 *********************************************
	 ******* PromotionApplicationService*******
	 *********************************************
	 */
	private PromotionApplicationService application;

	@AuthenticatePolicy({ Client.USER })
	public void loadPromotionApplicationService(PromotionApplicationService promotionApplicationService) {
		checkAuthentication();

		this.application = promotionApplicationService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public OrderInfo applyPromotion(OrderInfo info) {
		checkAuthentication();

		return application.applyPromotion(info);
	}

}
