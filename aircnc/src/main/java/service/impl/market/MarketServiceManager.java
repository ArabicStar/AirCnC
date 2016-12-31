package service.impl.market;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unsupportedOpEx;

import java.util.List;

import service.market.MarketService;
import service.member.MemberCreditService;
import service.query.OrderQueryService;
import utils.info.order.OrderStatus;
import vo.member.MemberVo;
import vo.order.OrderVo;

public class MarketServiceManager implements MarketService{
	
	private static MarketService instance;

	public static MarketService launch(OrderQueryService OrderQueryService
			, MemberCreditService memberCreditService) {
		if (instance != null)
			throw duplicateSingletonEx();
		instance = new MarketServiceManager(OrderQueryService, memberCreditService);
		return getInstance();
	}

	public static  MarketService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private OrderQueryService orderQueryService;
	private MemberCreditService creditService;
	
	public MarketServiceManager(OrderQueryService orderQueryService, MemberCreditService memberCreditService){
		this.orderQueryService = orderQueryService;
		this.creditService = memberCreditService;
	}
	
	private List<OrderVo> bufferedOrderList;
	
	@Override
	public List<OrderVo> getAbnormalOrder() {
		if (orderQueryService == null)
			throw unsupportedOpEx("get abnormal orders");
		
			List<OrderVo> res = orderQueryService.getOrdersOfStatus(OrderStatus.APPEALING);

			// given id not exists, return
			if (res == null)
				return null;
			
			bufferedOrderList = res;

			return bufferedOrderList;
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
