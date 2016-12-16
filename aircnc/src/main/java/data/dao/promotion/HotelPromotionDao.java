package data.dao.promotion;

import po.promotion.HotelPromotionPo;

public interface HotelPromotionDao {
	public boolean addHotelPromotion(HotelPromotionPo po);

	public boolean deleteHotelPromotion(long id);

	public boolean updateHotelPromotion(HotelPromotionPo po);

	public HotelPromotionPo findHotelPromotion(long id);
}
