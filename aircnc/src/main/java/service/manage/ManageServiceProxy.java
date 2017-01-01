package service.manage;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import utils.info.hotel.HotelInfo;
import utils.info.market.MarketInfo;
import utils.info.member.MemberInfo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.market.MarketVo;
import vo.market.MarketVoBuilder;

public class ManageServiceProxy extends AccessSecureProxy
		implements ManageHotelService, ManageMarketService, ManageMemberService {
	private static ManageServiceProxy instance;

	public static ManageServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageServiceProxy(clientId);
	}

	public static ManageServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private ManageMemberService manageMemberService;
	private ManageHotelService manageHotelService;
	private ManageMarketService manageMarketService;
	
	private ManageServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 ***************************
	 * Actual manager loader
	 ***************************
	 */
	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public void loadMemberManageService(ManageMemberService memberService) {
		checkAuthentication();

		this.manageMemberService = memberService;
	}

	@AuthenticatePolicy({ Client.USER, Client.MANAGE })
	public void loadHotelManageService(ManageHotelService hotelService) {
		checkAuthentication();

		this.manageHotelService = hotelService;
	}

	@AuthenticatePolicy({ Client.USER, Client.HOTEL, Client.MARKET , Client.MANAGE})
	public void loadMarketManageService(ManageMarketService marketService) {
		checkAuthentication();

		this.manageMarketService = marketService;
	}
	
	
	/*
	 ********************************************
	 * ManageMemberService method proxy
	 ********************************************
	 */
	@Override
	public boolean ModifyMemberInfo(MemberInfo memberInfo) {
		checkAuthentication();
		return manageMemberService.ModifyMemberInfo(memberInfo);
	}

	@Override
	public MemberInfo getMemberInfo(String id) {
		checkAuthentication();
		return manageMemberService.getMemberInfo(id);
	}
	
	/*
	 ********************************************
	 * ManageMarketService method proxy
	 ********************************************
	 */
	@Override
	public MarketVo AddMarketInfo(MarketVoBuilder newMarketInfo, int passwordHash) {
		checkAuthentication();
		return manageMarketService.AddMarketInfo(newMarketInfo, passwordHash);
	}

	@Override
	public boolean ModifyMarketInfo(MarketInfo marketInfo) {
		checkAuthentication();
		return manageMarketService.ModifyMarketInfo(marketInfo);
	}

	@Override
	public MarketInfo getMarketInfo(String id) {
		checkAuthentication();
		return manageMarketService.getMarketInfo(id);
	}

	@Override
	public boolean deleteMarketInfo(String id) {
		checkAuthentication();
		return manageMarketService.deleteMarketInfo(id);
	}
	
	/*
	 ********************************************
	 * ManageHotelService method proxy
	 ********************************************
	 */
	@Override
	public HotelVo AddHotelInfo(HotelVoBuilder newHotelInfo, int passwordHash) {
		checkAuthentication();
		return manageHotelService.AddHotelInfo(newHotelInfo, passwordHash);
	}

	@Override
	public boolean ModifyHotelInfo(HotelInfo hotelInfo) {
		checkAuthentication();
		return manageHotelService.ModifyHotelInfo(hotelInfo);
	}

	@Override
	public HotelInfo getHotelInfo(int id) {
		checkAuthentication();
		return manageHotelService.getHotelInfo(id);
	}
	
	@Override
	public HotelInfo getHotelInfoByName(String name) {
		checkAuthentication();
		return manageHotelService.getHotelInfoByName(name);
	}
	
	@Override
	public boolean deleteHotelInfo(int id) {
		checkAuthentication();
		return manageHotelService.deleteHotelInfo(id);
	}

}
