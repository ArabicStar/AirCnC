package service.impl.hotel;

import data.dao.hotel.HotelDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVoBuilder;
import vo.hotel.HotelVo;

public class HotelAccountManager implements HotelAccountService{

	private HotelDao dao;

	private boolean isLogined = false;
	/**
	 * Logined hotel info.<br>
	 * Actually, it should be a HotelPo instance, so casts it when neccessary.
	 * <br>
	 */
	private HotelPo currentAccount = null;

	public HotelAccountManager(HotelDao dao) {
		this.dao = dao;
	}

	@Override
	public HotelInfo register(HotelVoBuilder newHotelInfo, int passwordHash) {
		if (newHotelInfo == null)
			return null;


		// set id and password, build po
		HotelVo newHotelVo = newHotelInfo.getHotelInfo();
		HotelPo newHotelPo = new HotelPoBuilder(newHotelVo).setPasswordHash(passwordHash).getHotelInfo();

		// add new hotel
		boolean result = dao.addHotel(newHotelPo);
		if (result)
			return newHotelVo;

		return HotelVoBuilder.invalidInfo();
	}


	@Override
	public HotelInfo login(int id, int passwordHash) {
		if (!HotelInfo.checkID(id))
			return null;

		HotelPo hotelAccount = dao.findHotel(id);

		if (hotelAccount == null)// not exist
			return null;

		// password verify
		if (!checkPassword(hotelAccount, passwordHash))
			return HotelVoBuilder.invalidInfo();

		// set login status
		this.isLogined = true;
		this.currentAccount = hotelAccount;
		return currentAccount;
	}

	@Override
	public boolean logout() {
		// set logout status
		this.isLogined = false;
		this.currentAccount = null;
		return true;
	}

	@Override
	public boolean isLogined() {
		return isLogined;
	}

	@Override
	public HotelPo getCurrentAccount() {
		return currentAccount;
	}

	@Override
	public boolean existsHotel(int id) {
		if (!HotelInfo.checkID(id))
			return false;

		return dao.existHotel(id);
	}

	private static boolean checkPassword(HotelPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}

	@Override
	public void refreshCurrentAccount() {
		currentAccount = dao.findHotel(currentAccount.getId());
	}

}
