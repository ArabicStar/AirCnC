package service.hotel;

import java.util.List;
import java.util.Set;

import utils.info.hotel.HotelInfo;
import vo.order.comment.CommentVo;
import vo.promotion.PromotionVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(String name);

	public List<CommentVo> getHotelComment(int id);

	public Set<PromotionVo> getHotelActivePromotion(int id);

	public boolean updateInfo(HotelInfo modifiedInfo);
	
	public boolean updatePassword(int oldPass,int newPass);
}
