package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;
import data.dao.promotion.WebsitePromotionDao;
import po.promotion.PromotionPo;
import po.promotion.PromotionPoBuilder;
import service.market.MarketService;
import service.member.MemberCreditService;
import service.query.OrderQueryService;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public class MarketServiceManager implements MarketService{
	
	private static MarketService instance;

	public static MarketService launch(OrderQueryService OrderQueryService
			, MemberCreditService memberCreditService
			, WebsitePromotionDao websitePromotionDao) {
		if (instance != null)
			throw duplicateSingletonEx();
		instance = new MarketServiceManager(OrderQueryService, memberCreditService,
				websitePromotionDao);
		return getInstance();
	}

	public static  MarketService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private OrderQueryService orderQueryService;
	private MemberCreditService creditService;
	private WebsitePromotionDao promotionDao;
	
	public MarketServiceManager(OrderQueryService orderQueryService, MemberCreditService memberCreditService
			, WebsitePromotionDao promotionService){
		this.orderQueryService = orderQueryService;
		this.creditService = memberCreditService;
		this.promotionDao = promotionService;
	}
	
	private List<OrderVo> bufferedOrderList;
	
	@Override
	public List<OrderVo> getAbnormalOrder() {
		if (orderQueryService == null)
			throw unsupportedOpEx("get abnormal orders");
		
			List<OrderVo> res = orderQueryService.getOrdersOfStatus(OrderStatus.ABNORMAL);

			// given id not exists, return
			if (res == null)
				return null;
			
			bufferedOrderList = res;

			return bufferedOrderList;
		}
	
	@Override
	public boolean makeMarketPromotion(PromotionVo promotion) {
		if (promotionDao == null)
			throw unsupportedOpEx("make promotions");
		
	    PromotionPo po = new PromotionPoBuilder(promotion).getPromotionInfo();
	    
	    //这里还没写完
		return true;
	}

	@Override
	public boolean creditCharge(int money, String id) {
		if (creditService == null)
			throw unsupportedOpEx("credit charge");
		
		MemberVo vo = creditService.gainByCharge(money, id);
		
		if(vo == null)
			return false;
		
		return true;
		
	}
	
	
	
}
