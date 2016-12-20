package interactor.order;

public interface OrderInfoInteractor {
	public void getOrderInfo();
	
	/**
	 * for hotel to execute an order
	 */
	public void execute();
	
	/**
	 * for hotel to change abnormal order to unexecute;
	 */
	public void delay();
	
	
}
