package service.market;

import java.util.List;

import utils.info.market.MarketInfo;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public interface MarketInfoService {
	public MarketInfo getMarketInfo(String id);

	public List<OrderVo> getAllExceptionOrders(String id);
	
	public List<PromotionVo> getHotelPromotion(int id);

	public boolean updateBasicInfo(MarketInfo modifiedInfo);

	public boolean updateAdvancedInfo(MarketInfo modifiedInfo);

	public boolean updatePassword(int oldPwdHash, int newPwdHash);
}
