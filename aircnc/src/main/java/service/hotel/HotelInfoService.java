package service.hotel;

import java.util.List;

import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(String name);

	public List<OrderVo> getHotelOrder(int id);
	
	public List<CommentVo> getHotelComment(int id);

	public List<PromotionVo> getHotelPromotion(int id);

	public boolean updateInfo(HotelInfo modifiedInfo);
	
	public List<Room> getRooms(String name);
	
	public double getCheapestPrice(String name);
}
