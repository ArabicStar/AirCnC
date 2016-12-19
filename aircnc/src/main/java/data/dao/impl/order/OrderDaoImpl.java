package data.dao.impl.order;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import data.dao.order.OrderDao;
import data.dao.query.OrderQueryDao;
import po.order.OrderPo;
import po.order.OrderPoBuilder;
import utils.info.hotel.HotelInfoTemplate;
import utils.info.member.MemberInfoTemplate;
import utils.info.order.OrderStatus;

public enum OrderDaoImpl implements OrderDao, OrderQueryDao {
	INSTANCE;

	public OrderPo getOrder(String orderId) {
		if (!OrderPo.checkOrderId(orderId)) {
			System.err.println("订单号长度应该大于等于16位，且长度为偶数");
			throw illegalArgEx("Order Id String");
		}

		return execute(session -> {
			return (OrderPo) session.get(OrderPo.class, orderId);
		});
	}

	public boolean updateOrder(OrderPo orderPo) {
		if (orderPo == null) {
			return false;
		}
			
		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			OrderPo old = session.get(OrderPo.class, orderPo.getOrderId());
			if (flag = Boolean.valueOf(old != null)) {
				OrderPoBuilder.updatePo(orderPo, old);
			}

			return flag;
		});

	}

	public boolean addOrder(OrderPo newPo) {
		if (newPo == null) {
			return false;
		}


		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			if (flag = Boolean.valueOf(session.get(OrderPo.class, newPo.getOrderId()) == null)) {
				session.save(newPo);
			}
			return flag;
		});
	}

	public boolean existsOrder(String orderId) {
		return execute(session -> {
			return (OrderPo) session.get(OrderPo.class, orderId) != null;
		});
	}

	public boolean deleteOrder(String orderId) {
		// The order that exists can be deleted
		if (!existsOrder(orderId)) {
			return false;
		}

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			OrderPo toDelete = session.get(OrderPo.class, orderId);
			if (flag = Boolean.valueOf(toDelete != null)) {
				session.delete(toDelete);
			}
			return flag;
		});
	}

	@Override
	public List<OrderPo> searchByMember(String memberId) {
		if (!MemberInfoTemplate.checkID(memberId)) {
			throw illegalArgEx("Member id");
		}
		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			final Criterion memIdCond = Restrictions.eq("userId", new Integer(memberId));

			criteria.add(memIdCond);

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

	@Override
	public List<OrderPo> searchByHotel(int hotelId) {
		if (!HotelInfoTemplate.checkID(hotelId))
			throw illegalArgEx("Hotel id");

		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			final Criterion hotelIdCond = Restrictions.eq("hotelId", new Integer(hotelId));

			criteria.add(hotelIdCond);

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

	@Override
	public List<OrderPo> searchByStatus(OrderStatus status) {
		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			final Criterion statusCond = Restrictions.eq("orderStatus", status);

			criteria.add(statusCond);

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

}
