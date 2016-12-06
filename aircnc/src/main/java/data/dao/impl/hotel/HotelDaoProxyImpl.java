package data.dao.impl.hotel;

import data.dao.hotel.HotelDaoProxy;
import utils.proxy.AuthenticatePolicy.Client;

public class HotelDaoProxyImpl extends HotelDaoProxy{

	private HotelDaoImpl hdi;
	
	protected HotelDaoProxyImpl(Client clientId) {
		super(clientId);
	}

	@Override
	public void loadHotelDao() {
		if (hdi == null)
			hdi = new HotelDaoImpl();

		loadHotelDao(hdi);
		
	}

	
	

}
