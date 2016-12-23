package interactor.order;

public interface UserOrderOperationInteractor {
	public boolean tryMakeOrder();

	public boolean makeOrder();

	public boolean cancelOrder();
}
