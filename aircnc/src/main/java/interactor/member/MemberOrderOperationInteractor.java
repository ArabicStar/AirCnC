package interactor.member;

public interface MemberOrderOperationInteractor {
	public boolean tryMakeOrder();

	public boolean makeOrder();

	public boolean cancelOrder();

	public boolean appealOrder();

	public boolean commentOrder();
}
