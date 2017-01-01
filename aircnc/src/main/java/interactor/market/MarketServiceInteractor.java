package interactor.market;

public interface MarketServiceInteractor {

	public void getAbnormalOrder();

	public void approveOrder();

	public void creditCharge();

	public void getLevelStrategy();

	public void updateLevelStrategy();
	
	public void getUnexecutedOrder();
}
