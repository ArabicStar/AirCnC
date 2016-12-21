package service.impl.order;

import java.util.ArrayList;
import java.util.List;

import data.dao.impl.order.OrderDaoImpl;
import data.dao.query.OrderQueryDao;
import po.order.OrderPo;
import service.query.OrderQueryService;
import utils.info.order.OrderStatus;
import vo.order.OrderVo;

public class OrderQueryServiceImpl implements OrderQueryService{
	OrderQueryDao dao = OrderDaoImpl.INSTANCE;
	@Override
	public List<OrderVo> getMemberOrders(String memberId) {
		List<OrderVo> list = new ArrayList<>();
		List<OrderPo> tempList = dao.searchByMember(memberId);
		for(int i = 0; i < tempList.size(); i++) {
			list.add(tempList.get(i).orderPo2Vo());
		}
		return list;
	}

	@Override
	public List<OrderVo> getHotelOrders(int hotelId) {
		List<OrderVo> list = new ArrayList<>();
		List<OrderPo> tempList = dao.searchByHotel(hotelId);
		for(int i = 0; i < tempList.size(); i++) {
			list.add(tempList.get(i).orderPo2Vo());
		}
		return list;
	}

	@Override
	public List<OrderVo> getOrdersOfStatus(OrderStatus status) {
		List<OrderVo> list = new ArrayList<>();
		List<OrderPo> tempList = dao.searchByStatus(status);
		for(int i = 0; i < tempList.size(); i++) {
			list.add(tempList.get(i).orderPo2Vo());
		}
		return list;
	}

}
