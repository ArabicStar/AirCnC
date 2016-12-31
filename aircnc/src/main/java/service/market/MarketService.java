package service.market;

import java.util.List;

import utils.info.level.LevelStrategy;
import vo.order.OrderVo;

public interface MarketService {

	/**
	 * @return 获取异常订单
	 */
	public List<OrderVo> getAbnormalOrder();

	public boolean creditCharge(int money, String id);

	public LevelStrategy getLevelStrategy();

	public boolean updateLevelStrategy(LevelStrategy ls);
}
