package service.impl.hotel;

import java.util.List;

import data.dao.hotel.HotelDao;
import data.dao.member.MemberDao;
import data.dao.promotion.PromotionDao;
import po.hotel.HotelPo;
import po.hotel.HotelPoBuilder;
import service.hotel.HotelAccountService;
import service.hotel.HotelInfoService;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;
import vo.hotel.HotelVoBuilder;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;

public class HotelInfoManager implements HotelInfoService{
	private HotelDao hotelDao;
	private MemberDao memberDao;
	private PromotionDao promotionDao;
	
	private HotelAccountService accountService;
	
	public HotelInfoManager(HotelAccountService accountService,HotelDao hotelDao,MemberDao memberDao,PromotionDao promotionDao){
		this.accountService = accountService;
		this.hotelDao = hotelDao;
		this.memberDao = memberDao;
		this.promotionDao = promotionDao;
	}
	
	@Override
	public HotelInfo getHotelInfo(String name) {
		return new HotelVoBuilder(hotelDao.findHotel(name)).getHotelInfo();
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
	public List<HotelVo> getHotelPromotion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInfo(HotelInfo modifiedInfo) {
		if (!accountService.isLogined())
			throw new IllegalStateException("No Hotel Login");

		HotelPo po = (HotelPo) accountService.getCurrentAccount();

		if (modifiedInfo.getId()!=po.getId())
			throw new IllegalArgumentException("Incorresponding Hotel Info");

		return hotelDao
				.updateHotel(new HotelPoBuilder(modifiedInfo).setPasswordHash(po.getPasswordHash()).getHotelInfo());
	}

	

}
