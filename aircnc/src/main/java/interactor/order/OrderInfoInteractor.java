package interactor.order;

public interface OrderInfoInteractor {
	/**
	 * for hotel to get an order
	 */
	public void getOrderInfo();
	
	/**
	 * for hotel to execute an order
	 */
	public void execute();
	
	/**
	 * for hotel to change abnormal order to unexecute;
	 */
	public void delay();
	
	
	public void getOrderInfoById(String orderId);
	
	public void getOrderInfoByHotel();
	
	public void makeComment();
	
	public void makeAppeal();
	
	public void repeal();
	
}
