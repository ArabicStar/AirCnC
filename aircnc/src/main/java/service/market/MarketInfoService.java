package service.market;

import java.util.List;

import utils.info.market.MarketInfo;
import vo.order.OrderVo;

public interface MarketInfoService {
	public MarketInfo getMarketInfo(String id);

	public boolean updateInfo(MarketInfo modifiedInfo);
}
