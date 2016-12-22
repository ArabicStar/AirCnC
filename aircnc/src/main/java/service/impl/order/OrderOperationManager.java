package service.impl.order;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.illegalArgEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;
import java.util.Random;

import data.dao.order.OrderDao;
import po.order.OrderPo;
import po.order.OrderPoBuilder;
import service.member.MemberCreditService;
import service.order.OrderOperationService;
import service.promotion.PromotionApplicationService;
import service.query.HotelQueryService;
import service.query.MemberQueryService;
import utils.info.member.MemberInfo;
import utils.info.order.OrderInfo;
import utils.info.order.OrderStatus;
import vo.member.MemberVoBuilder;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class OrderOperationManager implements OrderOperationService {
	/* Singleton */
	private static OrderOperationManager instance;

	public static OrderOperationManager launch(OrderDao dao, HotelQueryService hotelQuery,
			MemberQueryService memberQuery, MemberCreditService creditService, PromotionApplicationService promotion) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderOperationManager(dao, hotelQuery, memberQuery, creditService, promotion);
	}

	public static OrderOperationManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param dao
	 * @param hotelQuery
	 * @param memberQuery
	 * @param creditService
	 * @param promotion
	 */
	private OrderOperationManager(OrderDao dao, HotelQueryService hotelQuery, MemberQueryService memberQuery,
			MemberCreditService creditService, PromotionApplicationService promotion) {
		super();
		this.dao = dao;
		this.hotelQuery = hotelQuery;
		this.memberQuery = memberQuery;
		this.creditService = creditService;
		this.promotion = promotion;
	}

	private OrderDao dao;
	private HotelQueryService hotelQuery;
	private MemberQueryService memberQuery;
	private MemberCreditService creditService;
	private PromotionApplicationService promotion;

	@Override
	public OrderInfo makeOrder(OrderVoBuilder newOrder, int hotelId) {
		if (newOrder == null)
			throw illegalArgEx("Order info", newOrder);

		String id = generateId(hotelId);
		OrderInfo newOrderInfo = promotion
				.applyPromotion(newOrder.setOrderId(id).setStatus(OrderStatus.UNEXECUTED).getOrderInfo());
		OrderPo newOrderPo = new OrderPoBuilder(newOrderInfo).getOrderInfo();

		if (dao.addOrder(newOrderPo))
			return newOrderInfo;

		return OrderVoBuilder.invalidOrderInfo();
	}

	@Override
	public MemberInfo executeOrder(OrderVo info) {
		if (!verifyOrder(info, OrderStatus.UNEXECUTED))
			throw illegalArgEx("Order info", info);

		OrderVo executedVo = new OrderVoBuilder(info).setStatus(OrderStatus.EXECUTED).getOrderInfo();
		OrderPo executedPo = new OrderPoBuilder(executedVo).getOrderInfo();

		if (dao.updateOrder(executedPo))
			return creditService.gainByOrderExecution(executedVo);

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberInfo cancelOrder(OrderVo info) {
		if (!verifyOrder(info, OrderStatus.UNEXECUTED))
			throw illegalArgEx("Order info", info);

		OrderVo canceledVo = new OrderVoBuilder(info).setStatus(OrderStatus.REPEALED).getOrderInfo();
		OrderPo canceledPo = new OrderPoBuilder(canceledVo).getOrderInfo();
		if (dao.updateOrder(canceledPo))
			return creditService.reduceByCancel(canceledVo);

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberInfo overdueOrder(OrderVo info) {
		if (!verifyOrder(info, OrderStatus.UNEXECUTED))
			throw illegalArgEx("Order info", info);

		OrderVo overdueVo = new OrderVoBuilder(info).setStatus(OrderStatus.ABNORMAL).getOrderInfo();
		OrderPo overduePo = new OrderPoBuilder(overdueVo).getOrderInfo();

		if (dao.updateOrder(overduePo))
			return creditService.reduceByOverdue(overdueVo);

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberInfo delayOrder(OrderVo info) {
		if (!verifyOrder(info, OrderStatus.ABNORMAL))
			throw illegalArgEx("Order info", info);

		OrderVo delayedVo = new OrderVoBuilder(info).setStatus(OrderStatus.EXECUTED).getOrderInfo();
		OrderPo delayedPo = new OrderPoBuilder(delayedVo).getOrderInfo();

		if (dao.updateOrder(delayedPo))
			return creditService.recoverByDelay(delayedVo);

		return MemberVoBuilder.invalidInfo();
	}

	@Override
	public MemberInfo appealOrder(OrderVo info) {
		if (!verifyOrder(info, OrderStatus.ABNORMAL))
			throw illegalArgEx("Order info", info);

		OrderVo appealedVo = new OrderVoBuilder(info).setStatus(OrderStatus.ABNORMAL).getOrderInfo();
		OrderPo appealedPo = new OrderPoBuilder(appealedVo).getOrderInfo();

		if (dao.updateOrder(appealedPo))
			return creditService.recoverByAppeal(appealedVo);

		return MemberVoBuilder.invalidInfo();
	}

	private boolean verifyOrder(OrderInfo info, OrderStatus status) {
		if (memberQuery.searchById(info.getMember().getId()) == null)
			return false;

		if (hotelQuery.findById(info.getHotel().getId()) == null)
			return false;

		return info.isValid() && status == info.getStatus();
	}

	private static final Random R = new Random(System.currentTimeMillis());

	private String generateId(int hotelId) {
		StringBuilder builder = new StringBuilder();
		builder.append(OrderVo.getDateFormatter().format(LocalDateTime.now()));

		final int length = Math.max(String.valueOf(hotelId).length(), 4);

		String header = builder.append(formatInt(hotelId, length)).toString();

		int bound = length * 10;
		String stringID = header + formatInt(R.nextInt(bound), length);

		// check and generate loop
		for (int numId = R.nextInt(bound); dao.existsOrder(stringID); numId = R.nextInt(bound))
			stringID = header + formatInt(numId, length);

		return stringID;
	}

	private static final String formatInt(int value, int length) {
		return String.format("%" + length + "d", value);
	}
}
