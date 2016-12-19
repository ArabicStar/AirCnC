package service.impl.hotel;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.hotel.HotelDao;
import data.dao.member.MemberDao;
import data.dao.promotion.HotelPromotionDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import utils.info.hotel.RoomBuilder;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public class HotelInfoManager implements HotelInfoService {
	private HotelDao hotelDao;
	private MemberDao memberDao;
	private HotelPromotionDao promotionDao;

	private HotelAccountService accountService;

	public HotelInfoManager(HotelAccountService accountService, HotelDao hotelDao, MemberDao memberDao,
			HotelPromotionDao promotionDao) {
		this.accountService = accountService;
		this.hotelDao = hotelDao;
		this.memberDao = memberDao;
		this.promotionDao = promotionDao;
	}

	@Override
	public HotelInfo getHotelInfo(String name) {
		return new HotelVoBuilder(hotelDao.findHotelByName(name)).getHotelInfo();
	}

	@Override
	public List<OrderVo> getHotelOrder(int id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentVo> getHotelComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionVo> getHotelPromotion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInfo(HotelInfo modifiedInfo) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Hotel Login");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (modifiedInfo.getId() != po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel Info");

		return hotelDao
				.updateHotel(new HotelPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getHotelInfo());
	}

	@Override
	public List<Room> getRooms(String name) {
		return hotelDao.findHotelByName(name).getRooms().stream().map(r -> new RoomBuilder(r).getRoomInfo())
				.collect(Collectors.toList());

	}

	@Override
	public double getCheapestPrice(String name) {

		return hotelDao.findHotelByName(name).getRooms().stream().mapToDouble(Room::getPrice).min().getAsDouble();

	}

	public void setHotel(HotelInfo info) {
		// TODO Auto-generated method stub
		
	}

}
