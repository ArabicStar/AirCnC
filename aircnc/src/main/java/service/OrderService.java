package service;

import java.util.List;

import po.order.OrderPo;
import vo.OrderVo;

public interface OrderService {
	
	/**
	 * @param hotelId
	 * @return	获取酒店所有订单列表
	 */
	public List<OrderVo> getAllOrders(int hotelId);
	
	/**
	 * @param hotelId
	 * @return	获取酒店未执行订单列表
	 */
	public List<OrderVo> getUnfinishedOrders(int hotelId);
	
	/**
	 * @param hotelId
	 * @return	获取酒店已执行订单列表
	 */
	public List<OrderVo> getFinishedOrders(int hotelId);
	
	/**
	 * @param hotelId
	 * @return	获取酒店异常订单列表
	 */
	public List<OrderVo> getAbnormalOrders(int hotelId);
	
	/**
	 * @param orderId
	 * @return	获取订单用户编号
	 */
	public int getOrderUser(int orderId);
	
	/**
	 * @param orderId
	 * @return	获取订单价值
	 */
	public int getOrderPrice(int orderId);
	
	
	/**
	 * @param orderId
	 * @return	执行正常订单
	 */
	public boolean finishOrder(int orderId);
	
	/**
	 * @param orderId
	 * @param delayTime
	 * @return	延期异常订单
	 */
	public boolean delayOrder(int orderId,String delayTime);
	
	/**
	 * 
	 * @param orderId
	 * @return 撤销未执行订单
	 */
	public boolean revealOrder(int orderId);
	

}
