package service.hotel;

import java.util.List;
import java.util.Set;

import utils.condition.Condition;
import utils.info.hotel.HotelInfo;
import utils.info.hotel.Room;
import utils.info.order.OrderStatus;
import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(String name);
	
	public List<OrderVo> getHotelAllOrders(int id);

	public List<OrderVo> getHotelOrdersByStatus(int id, OrderStatus status);
	
	public List<CommentVo> getHotelComment(int id);

	public Set<PromotionVo> getHotelActivePromotion(int id);
	
	public Set<PromotionVo> getHotelAllPromotions(int hotelId);

	public boolean updateInfo(HotelInfo modifiedInfo);
	
	public List<HotelVo> findByCondition(Condition cond);

	
}
