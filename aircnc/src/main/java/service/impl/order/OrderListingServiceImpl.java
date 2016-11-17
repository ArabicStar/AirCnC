package service.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.MemberDao;
import data.dao.OrderDao;
import data.dao.impl.MemberDaoImpl;
import data.dao.impl.OrderDaoImpl;
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
	 * FIXME: 这个构造函数涉及到的地方特别多，注意修改用到OrderServiceImpl的地方
	 * @param hotelId
	 */
	public OrderListingServiceImpl(int hotelId){
		this.hotelId = hotelId;
		this.orderDao = OrderDaoImpl.getInstance();
		this.memberDao = new MemberDaoImpl();
		this.hotelOrderList = orderDao.getOrders(hotelId);
	}

	@Override
	public List<OrderVo> getAllOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
			OrderVo orderVo = new OrderVo(orderPo, memberPo);
			list.add(orderVo);
		}
		return list;
	}

	@Override
	public List<OrderVo> getUnfinishedOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 0){
				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
				OrderVo orderVo = new OrderVo(orderPo, memberPo);
				list.add(orderVo);
			}
		}
		return list;
	}

	@Override
	public List<OrderVo> getFinishedOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 1){
				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
				OrderVo orderVo = new OrderVo(orderPo, memberPo);
				list.add(orderVo);
			}
		}
		return list;
	}

	@Override
	public List<OrderVo> getAbnormalOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (OrderPo orderPo : hotelOrderList) {
			if(orderPo.getStatus() == 2){
				MemberPo memberPo = memberDao.findMember(Integer.toString(orderPo.getUserId()));
				OrderVo orderVo = new OrderVo(orderPo, memberPo);
				list.add(orderVo);
			}
		}
		return list;
	}

}
