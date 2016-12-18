package service.order;

/**
 * 获取酒店详细信息的接口
 * @author Water
 *
 */
public interface OrderDetailService {
	/**
	 * @param orderId
	 * @return	获取订单用户编号
	 */
	public int getOrderUser(String orderId);
	
	/**
	 * @param orderId
	 * @return	获取订单价值
	 */
	public double getOrderOriginalPrice(String orderId);
}
