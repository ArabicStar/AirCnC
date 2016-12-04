package service.hotel;

import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy.Client;

public abstract class HotelServiceProxy extends AccessSecureProxy{

	protected HotelServiceProxy(Client clientId) {
		super(clientId);
		// TODO Auto-generated constructor stub
	}
	
}
