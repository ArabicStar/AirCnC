package service.market;

import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class MarketServiceProxy extends AccessSecureProxy{

	protected MarketServiceProxy(Client clientId) {
		super(clientId);
		// TODO Auto-generated constructor stub
	}
}
