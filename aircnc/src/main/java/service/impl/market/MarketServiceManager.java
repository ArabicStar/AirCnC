package service.impl.market;

import java.util.List;

import service.market.MarketService;
import service.member.MemberCreditService;
import vo.order.OrderVo;

public class MarketServiceManager implements MarketService{
	
	private MemberCreditService creditService;
	
	public MarketServiceManager(MemberCreditService service){
		this.creditService = service;
	}
	
	@Override
	public List<OrderVo> getAbnormalOrder() {
		//让我先等一下query的接口
		return null;
	}
	
//	@Override
//	public List<PromotionVo> getMarketPromotion(String id){
//		让我先等一下PromotionVo的出现
//	}
	
	
}
