package data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.dao.OrderDao;
import data.datahelper.DataFactory;
import data.datahelper.OrderDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.OrderPo;

public class OrderDaoImpl implements OrderDao{
	
	private Map<Integer, OrderPo> map;
	
	private OrderDataHelper orderDataHelper;
	
	private DataFactory dataFactory;
	
	private static OrderDaoImpl orderDataServiceImpl;
	
	public static OrderDaoImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDaoImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			orderDataHelper = dataFactory.getOrderDataHelper();
			map = orderDataHelper.getOrderData();
		}
	}

	public OrderPo getOrder(int orderId) {
		OrderPo orderPo = map.get(orderId);
		return orderPo;
	}

	public List<OrderPo> getOrders(int hotelId) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		Iterator<Map.Entry<Integer,OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			if(orderPo.getHotelId() == hotelId){
				orderList.add(orderPo);
			}
		}
		return orderList;
	}

	public boolean updateOrder(OrderPo orderPo) {
		int orderId = orderPo.getId();
		if(map.get(orderId) != null){
			map.put(orderId,orderPo);
			orderDataHelper.updateOrderData(map);
			return true;
		}
		return false;
		
	}

	public boolean addOrderPo(OrderPo orderPo) {
		/*
		 * 列表中添加订单并写入数据文件中
		 */
		return false;
	}

	public boolean deleteOrderPo(int orderId) {
		/*
		 * 列表中删除订单并更新数据文件
		 */
		return false;
	}

}
