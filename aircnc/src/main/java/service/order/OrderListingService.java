package service.order;

import java.util.List;

import vo.order.OrderVo;

/**
 * 这是用来显示酒店列表的接口
 * @author Water
 *
 */
public interface OrderListingService {
	/**
	 * @param hotelId
	 * @return	获取酒店所有订单列表
	 */
	public List<OrderVo> getAllOrders();
	
	/**
	 * @param hotelId
	 * @return	获取酒店未执行订单列表
	 */
	public List<OrderVo> getUnexecutedOrders();
	
	/**
	 * @param hotelId
	 * @return	获取酒店已执行订单列表
	 */
	public List<OrderVo> getExecutedOrders();
	
	/**
	 * @param hotelId
	 * @return	获取酒店异常订单列表
	 */
	public List<OrderVo> getAbnormalOrders();
}
