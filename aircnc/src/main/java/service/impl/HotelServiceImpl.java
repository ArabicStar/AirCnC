package service.impl;

import java.util.List;

import data.dao.HotelDao;
import data.dao.OrderDao;
import data.dao.impl.HotelDaoImpl;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import service.HotelService;
import vo.CommentVo;
import vo.HotelVo;
import vo.order.OrderVo;

public class HotelServiceImpl implements HotelService {
	
	private String hotelId;
	private HotelDao hotelDao;
	private OrderDao orderDao;
	private List<CommentVo> commentList;
	private List<OrderVo> orderList;

	public HotelServiceImpl(String hotelId){
		this.hotelId = hotelId;
		hotelDao = HotelDaoImpl.getInstance();
		orderDao = OrderDaoImpl.getInstance();
//		orderList = orderDao.getOrders(hotelId);
	}
	
	
	@Override
	public double getHotelLowestPrice(String hotelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderVo> checkMyOrder(String userId, String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelVo getHotelDetailInfo(String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHotelSimpleInfo(String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentVo> getHotelComments(String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

}
