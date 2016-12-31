package service.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;

import utils.info.market.MarketInfo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;
import utils.proxy.AuthenticatePolicy.Client;
import vo.order.OrderVo;

public class MarketServiceProxy extends AccessSecureProxy 
	implements MarketAccountService, MarketInfoService, MarketService {
	
	private static MarketServiceProxy instance;

	public static MarketServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketServiceProxy(clientId);
	}

	public static MarketServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	protected MarketServiceProxy(Client clientId) {
		super(clientId);

	}
	
	
	private MarketInfoService infoService;
	private MarketAccountService accountService;
	private MarketService marketService;
	
	/*
	 ***************************
	 * Actual manager loader
	 ***************************
	 */
	@AuthenticatePolicy({ Client.MARKET })
	public void loadAccountService(MarketAccountService accountService) {
		checkAuthentication();

		this.accountService = accountService;
	}

	@AuthenticatePolicy({ Client.MANAGE})
	public void loadInfoService(MarketInfoService infoService) {
		checkAuthentication();

		this.infoService = infoService;
	}
	
	@AuthenticatePolicy({ Client.MARKET})
	public void loadMarketService(MarketService marketService) {
		checkAuthentication();

		this.marketService = marketService;
	}
	
	
	
	/*
	 ********************************************
	 * MarketInfoService method proxy
	 ********************************************
	 */
	
	@Override
	@AuthenticatePolicy({ Client.MARKET ,Client.MANAGE })
	public MarketInfo getMarketInfo(String id) {
		checkAuthentication();
		
		return infoService.getMarketInfo(id);
	}


	/*
	 ********************************************
	 * MarketAccountService method proxy
	 ********************************************
	 */
	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MarketInfo login(String id, int passwordHash) {
		checkAuthentication();
		return accountService.login(id, passwordHash);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public boolean logout() {
		checkAuthentication();
		return accountService.logout();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public boolean isLoggedin() {
		checkAuthentication();
		return accountService.isLoggedin();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MarketInfo refreshCurrentAccount() {
		checkAuthentication();
		return accountService.refreshCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MarketInfo getCurrentAccount() {
		checkAuthentication();
		return accountService.getCurrentAccount();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public boolean existsMarket(String id) {
		checkAuthentication();
		return accountService.existsMarket(id);
	}
	
	
	/*
	 ********************************************
	 * MarketService method proxy
	 ********************************************
	 */

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public List<OrderVo> getAbnormalOrder() {
		checkAuthentication();
		return marketService.getAbnormalOrder();
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public boolean creditCharge(int money, String id) {
		checkAuthentication();
		return marketService.creditCharge(money, id);
	}
}
