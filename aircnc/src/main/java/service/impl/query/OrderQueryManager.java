package service.impl.query;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.List;
import java.util.stream.Collectors;

import data.dao.query.OrderQueryDao;
import service.query.OrderQueryService;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;
import vo.order.OrderVoBuilder;

public class OrderQueryManager implements OrderQueryService {
	/* Singleton */
	private static OrderQueryManager instance;

	public static OrderQueryManager launch(OrderQueryDao queryDao) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OrderQueryManager(queryDao);
	}

	public static OrderQueryManager getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	/**
	 * @param queryDao
	 */
	private OrderQueryManager(OrderQueryDao queryDao) {
		super();
		this.queryDao = queryDao;
	}

	private OrderQueryDao queryDao;

	@Override
	public List<OrderVo> getMemberOrders(String memberId) {
		return queryDao.searchByMember(memberId).stream().map(po -> new OrderVoBuilder(po).getOrderInfo())
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderVo> getHotelOrders(int hotelId) {
		return queryDao.searchByHotel(hotelId).stream().map(po -> new OrderVoBuilder(po).getOrderInfo())
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderVo> getOrdersOfStatus(OrderStatus status) {
		return queryDao.searchByStatus(status).stream().map(po -> new OrderVoBuilder(po).getOrderInfo())
				.collect(Collectors.toList());
	}
}
