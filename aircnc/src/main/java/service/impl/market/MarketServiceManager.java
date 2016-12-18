package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.market.MarketDao;
import data.dao.member.CreditDao;
import data.dao.member.MemberDao;
import data.dao.order.OrderDao;
import service.impl.member.MemberCreditManager;
import service.market.MarketService;
import service.member.MemberCreditService;
import service.order.OrderListingService;
import service.promotion.WebsitePromotionApplicationService;
import service.query.OrderQueryService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVo;
import vo.promotion.PromotionVo;

public class MarketServiceManager implements MarketService{
	
	private static MarketService instance;

	public static MarketService launch(OrderQueryService OrderQueryService
			, MemberCreditService memberCreditService
			, WebsitePromotionApplicationService websitePromotionApplicationService) {
		if (instance != null)
			throw duplicateSingletonEx();
		instance = new MarketServiceManager(OrderQueryService, memberCreditService,
				websitePromotionApplicationService);
		return getInstance();
	}

	public static  MarketService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private OrderQueryService orderQueryService;
	private MemberCreditService creditService;
	private WebsitePromotionApplicationService promotionService;
	
	public MarketServiceManager(OrderQueryService orderQueryService, MemberCreditService memberCreditService
			, WebsitePromotionApplicationService promotionService){
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
	
	private List<PromotionVo> bufferedPromotionList;
	
	@Override
	public List<PromotionVo> makeMarketPromotion(String id) {
		// TODO Auto-generated method stub
		return null;
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
