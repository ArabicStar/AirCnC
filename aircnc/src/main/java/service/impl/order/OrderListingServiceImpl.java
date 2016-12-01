package service.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.impl.member.MemberDaoImpl;
import data.dao.impl.order.OrderDaoImpl;
import data.dao.member.MemberDao;
import data.dao.order.OrderDao;
import po.member.MemberPo;
import po.order.OrderPo;
import service.order.OrderListingService;
import vo.order.OrderVo;

public class OrderListingServiceImpl implements OrderListingService {

	private int hotelId;

	private List<OrderPo> hotelOrderList;

	private OrderDao orderDao;

	private MemberDao memberDao;

	/**
	 * 用于获取各种状态的订单的构造方法
	 * @param hotelId 酒店的Id
	 */
	public OrderListingServiceImpl(int hotelId) {
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
		this.memberDao = new MemberDaoImpl();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}

	/**
	 * @param hotelId 酒店的Id
	 * @return 所有的订单
	 */
	@Override
	public List<OrderVo> getAllOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
			/**
			 * FIXME:这里的orderVo并没有设置该有的属性，
			 * 原因是在修改了构造方法后，为了让编译不报错
			 * {@link vo.order.OrderVo}
			 * 这里需要添加合适的构造方法
			 */
			OrderVo orderVo =new OrderVo(); //new OrderVo(orderPo, memberPo);
			list.add(orderVo);
		}
//		return list;
		return null;
	}

	/**
	 * @param hotelId 酒店的Id
	 * @return 未执行的订单
	 */
	@Override
	public List<OrderVo> getUnfinishedOrders(int hotelId) {
//		List<OrderVo> list = new ArrayList<OrderVo>();
//		for (OrderPo orderPo : hotelOrderList) {
//			if (orderPo.getStatus() == OrderStatus.UNEXECUTED) {
//				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
//				/**
//				 * FIXME:这里的orderVo并没有设置该有的属性，
//				 * 原因是在修改了构造方法后，为了让编译不报错
//				 * {@link vo.order.OrderVo}
//				 * 这里需要添加合适的构造方法
//				 */
//				OrderVo orderVo = new OrderVo();
//				list.add(orderVo);
//			}
//		}
//		return list;
		return null;
	}

	/**
	 * @param hotelId 酒店的Id
	 * @return 已执行的订单
	 */
	@Override
	public List<OrderVo> getFinishedOrders(int hotelId) {
//		List<OrderVo> list = new ArrayList<OrderVo>();
//		for (OrderPo orderPo : hotelOrderList) {
//			if (orderPo.getStatus() == OrderStatus.EXECUTED) {
//				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
//				/**
//				 * FIXME:这里的orderVo并没有设置该有的属性，
//				 * 原因是在修改了构造方法后，为了让编译不报错
//				 * {@link vo.order.OrderVo}
//				 * 这里需要添加合适的构造方法
//				 */
//				OrderVo orderVo = new OrderVo();
//				list.add(orderVo);
//			}
//		}
//		return list;
		return null;
	}

	/**
	 * @param hotelId 酒店的Id
	 * @return 异常的订单
	 */
	@Override
	public List<OrderVo> getAbnormalOrders(int hotelId) {
//		List<OrderVo> list = new ArrayList<OrderVo>();
//		for (OrderPo orderPo : hotelOrderList) {
//			if (orderPo.getStatus() == OrderStatus.ABNORMAL) {
//				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
//				/**
//				 * FIXME:这里的orderVo并没有设置该有的属性，
//				 * 原因是在修改了构造方法后，为了让编译不报错
//				 * {@link vo.order.OrderVo}
//				 * 这里需要添加合适的构造方法
//				 */
//				OrderVo orderVo = new OrderVo();
//				list.add(orderVo);
//			}
//		}
//		return list;
		return null;
	}

}
