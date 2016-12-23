package service.order;

import utils.proxy.AuthenticatePolicy.Client;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.proxy.AccessSecureProxy;
import utils.proxy.AuthenticatePolicy;

public class OrderServiceProxy extends AccessSecureProxy implements OrderInfoService, OrderOperationService {
	/* Singleton */
	private static OrderServiceProxy instance;

	public static OrderServiceProxy launch(Client clientId) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderServiceProxy(clientId);
	}

	public static OrderServiceProxy getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	protected OrderServiceProxy(Client clientId) {
		super(clientId);
	}

	/*
	 ***************************************
	 ******* OrderOperationService*******
	 ***************************************
	 */
	private OrderOperationService orderOperation;

	@AuthenticatePolicy({ Client.ALL })
	public void loadOrderOperationService(OrderOperationService orderOperationService) {
		checkAuthentication();

		this.orderOperation = orderOperationService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public OrderInfo makeOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.makeOrder(info);
	}

	@Override
	@AuthenticatePolicy({ Client.USER })
	public MemberInfo cancelOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.cancelOrder(info);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public MemberInfo executeOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.executeOrder(info);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL })
	public MemberInfo delayOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.delayOrder(info);
	}

	@Override
	@AuthenticatePolicy({ Client.HOTEL, Client.SERVER })
	public MemberInfo overdueOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.overdueOrder(info);
	}

	@Override
	@AuthenticatePolicy({ Client.MARKET })
	public MemberInfo appealOrder(OrderInfo info) {
		checkAuthentication();

		return orderOperation.appealOrder(info);
	}

	private OrderInfoService orderInfo;

	@AuthenticatePolicy({ Client.ALL })
	public void loadOrderInfoService(OrderInfoService orderInfoService) {
		checkAuthentication();

		this.orderInfo = orderInfoService;
	}

	@Override
	@AuthenticatePolicy({ Client.USER, Client.MARKET, Client.HOTEL, Client.SERVER })
	public OrderInfo findOrder(String orderId) {
		checkAuthentication();

		return orderInfo.findOrder(orderId);
	}

}
