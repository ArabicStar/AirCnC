package data.dao.impl.order;

import static data.hibernate.Hibernator.execute;
import static utils.exception.StaticExceptionFactory.illegalArgEx;

import java.util.List;

import org.hibernate.Criteria;
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
		if (!OrderPo.checkOrderId(orderId))
			throw illegalArgEx("Order Id String", orderId);

		return execute(session -> (OrderPo) session.get(OrderPo.class, orderId));
	}

	public boolean updateOrder(OrderPo orderPo) {
		if (orderPo == null)
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			OrderPo old = session.get(OrderPo.class, orderPo.getOrderId());
			if (flag = Boolean.valueOf(old != null))
				OrderPoBuilder.updatePo(orderPo, old);

			return flag;
		});

	}

	public boolean addOrder(OrderPo newPo) {
		if (newPo == null) {
			return false;
		}

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			if (flag = Boolean.valueOf(session.get(OrderPo.class, newPo.getOrderId()) == null))
				session.save(newPo);

			return flag;
		});
	}

	public boolean existsOrder(String orderId) {
		return execute(session -> (OrderPo) session.get(OrderPo.class, orderId) != null);
	}

	public boolean deleteOrder(String orderId) {
		// The order that exists can be deleted
		if (!existsOrder(orderId))
			return false;

		return execute(session -> {
			Boolean flag = Boolean.FALSE;

			OrderPo toDelete = session.get(OrderPo.class, orderId);
			if (flag = Boolean.valueOf(toDelete != null))
				session.delete(toDelete);

			return flag;
		});
	}

	@Override
	public List<OrderPo> searchByMember(String memberId) {
		if (!MemberInfoTemplate.checkID(memberId))
			throw illegalArgEx("Member id", memberId);

		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			criteria.add(Restrictions.eq("member", MemberInfoTemplate.convertID2Num(memberId)));

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

	@Override
	public List<OrderPo> searchByHotel(int hotelId) {
		if (!HotelInfoTemplate.checkID(hotelId))
			throw illegalArgEx("Hotel id", hotelId);

		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			criteria.add(Restrictions.eq("hotel", new Integer(hotelId)));

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

	@Override
	public List<OrderPo> searchByStatus(OrderStatus status) {
		return execute(session -> {
			final Criteria criteria = session.createCriteria(OrderPo.class);
			criteria.add(Restrictions.eq("status", status));

			@SuppressWarnings("unchecked")
			List<OrderPo> list = criteria.list();

			return list;
		});
	}

}
