package service.hotel;

import java.util.List;

import utils.info.hotel.HotelInfo;
import vo.hotel.HotelVo;
import vo.order.OrderVo;
import vo.order.comment.CommentVo;

public interface HotelInfoService {
	public HotelInfo getHotelInfo(int id);

	public List<OrderVo> getHotelOrder(int id);
	
	public List<CommentVo> getHotelComment(int id);

	public List<HotelVo> getHotelPromotion(int id);

	public boolean updateInfo(HotelInfo modifiedInfo);
}
