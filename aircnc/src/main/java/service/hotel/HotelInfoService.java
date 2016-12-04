package service.hotel;

import java.util.List;

import utils.info.hotel.HotelInfo;
import vo.order.comment.CommentVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(String id);

	public List<CommentVo> getHotelComment(String id);

//	public List<PromotionVo> getHotelPromotion(String id);

	public boolean updateInfo(HotelInfo modifiedInfo);
}
