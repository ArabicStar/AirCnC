package service.hotel;

import java.util.List;
import java.util.Set;

import utils.condition.Condition;
import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(String name);
	
	public List<CommentVo> getHotelComment(int id);

	public Set<PromotionVo> getHotelActivePromotion(int id);
	
	public Set<PromotionVo> getHotelAllPromotions(int hotelId);

	public boolean updateInfo(HotelInfo modifiedInfo);
	
	public List<HotelVo> findByCondition(Condition cond);

	
}
