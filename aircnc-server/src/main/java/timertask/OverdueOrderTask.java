package timertask;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.time.LocalDateTime;
import java.util.TimerTask;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.order.OrderDao;
import data.dao.query.OrderQueryDao;
import utils.info.order.OrderStatus;

public class OverdueOrderTask extends TimerTask {

	private OrderQueryDao orderQuery = OrderDaoImpl.INSTANCE;
	private OrderDao dao = OrderDaoImpl.INSTANCE;

	private void overdue() {
		final LocalDateTime now = LocalDateTime.now();
		orderQuery.searchByStatus(OrderStatus.UNEXECUTED).stream().filter(order -> order.getLastTime().isAfter(now))
				.peek(order -> order.setStatus(OrderStatus.ABNORMAL)).forEach(dao::updateOrder);
	}

	@Override
	public void run() {
		overdue();
	}

	/* Singleton */
	private static OverdueOrderTask instance;

	public static OverdueOrderTask launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new OverdueOrderTask();
	}

	public static OverdueOrderTask getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	/* Singleton */

	private OverdueOrderTask() {

	}

}
