package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;
import service.market.MarketService;
import service.member.MemberCreditService;
import service.promotion.WebsitePromotionManagementService;
import service.query.OrderQueryService;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVo;
import vo.promotion.WebsitePromotionVo;

public class MarketServiceManager implements MarketService{
	
	private static MarketService instance;

	public static MarketService launch(OrderQueryService OrderQueryService
			, MemberCreditService memberCreditService
			, WebsitePromotionManagementService websitePromotionService) {
		if (instance != null)
			throw duplicateSingletonEx();
		instance = new MarketServiceManager(OrderQueryService, memberCreditService,
				websitePromotionService);
		return getInstance();
	}

	public static  MarketService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private OrderQueryService orderQueryService;
	private MemberCreditService creditService;
	private WebsitePromotionManagementService promotionService;
	
	public MarketServiceManager(OrderQueryService orderQueryService, MemberCreditService memberCreditService
			, WebsitePromotionManagementService promotionService){
		this.orderQueryService = orderQueryService;
		this.creditService = memberCreditService;
		this.promotionService = promotionService;
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
	public boolean makeMarketPromotion(WebsitePromotionVo promotion) {
		if (promotionService == null)
			throw unsupportedOpEx("make website promotions");
	    
		return promotionService.addWebsitePromotion(promotion);
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

	@Override
	public boolean deletePromotion(WebsitePromotionVo promotion) {
		if (promotionService == null)
			throw unsupportedOpEx("make website promotions");
	    
		return promotionService.deleteWebsitePromotion(promotion);
	}

	@Override
	public boolean updatePromotion(WebsitePromotionVo vo) {
		if (promotionService == null)
			throw unsupportedOpEx("make website promotions");
	    
		return promotionService.updateWebsitePromotion(vo);
	}
	
	
	
}
