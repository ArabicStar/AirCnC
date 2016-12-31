package service.market;

import java.util.List;

import vo.order.OrderVo;

public interface MarketService {

	/**
	 * @return	获取异常订单
	 */
	public List<OrderVo> getAbnormalOrder();
	
	public boolean creditCharge(int money, String id);

}
