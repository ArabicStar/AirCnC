package service.order;

/**
 * 对订单的逻辑操作的接口
 * @author Water
 *
 */
public interface OrderLogicService {
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
