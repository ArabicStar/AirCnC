package interactor.impl.manage;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.unknownEx;

import interactor.manage.ManageMarketInteractor;
import interactor.utils.AlertHelper;
import presentation.manage.accessor.impl.MarketManageInfoAccessorImpl;
import presentation.manage.manager.impl.MarketManageInfoManagerImpl;
import service.manage.ManageMarketService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

public class ManageMarketCourier implements ManageMarketInteractor{
	/* singleton */
	private static ManageMarketInteractor instance;

	public static final ManageMarketInteractor launch(ManageMarketService handler) {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new ManageMarketCourier(handler);
	}

	public static final ManageMarketInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	/* singleton */

	private ManageMarketService handler;

	private ManageMarketCourier(ManageMarketService handler) {
		this.handler = handler;
	}

	@Override
	public boolean AddMarketInfo() {
		String title = getTitle();
		MarketInfo info = execute(title, () -> {
			MarketInfo tmp = handler.AddMarketInfo(new MarketVoBuilder(MarketManageInfoAccessorImpl.getInstance()
					.getAddedMarketVo()), MarketManageInfoAccessorImpl.getInstance().getPasswordHash());

			if (tmp == null)
				throw unknownEx();
			return tmp;
		});

		if (info != null)
			AlertHelper.alertSuccess(title, String.format("添加营销人员成功！该账号是%s。此账号将会作为登录凭据", info.getId()));
		else
			AlertHelper.alertFail(title, "添加失败！");
		MarketManageInfoManagerImpl.getInstance().setMarket(new MarketVoBuilder(info).getMarketInfo());
		return info != null;
	}

	@Override
	public boolean ModifyMarketInfo() {
		String title = getTitle();
		MarketInfo modified = MarketManageInfoAccessorImpl.getInstance().getModifiedMarketVo();
		boolean res = execute(title, () -> {
			String id = MarketManageInfoAccessorImpl.getInstance().getMarketId();
			if (id != null)
				return handler.ModifyMarketInfo(modified);

			alertFail(title, "Not input the modified info yet");
			return null;
		});

		MarketManageInfoManagerImpl.getInstance().setMarket(res ? new MarketVoBuilder(modified).getMarketInfo() : null);
		return modified != null;
	}

	@Override
	public boolean getMarketInfo() {
		String title = getTitle();

		MarketInfo info = execute(title, () -> {
			String id = MarketManageInfoAccessorImpl.getInstance().getMarketId();
			if (id != null)
				return handler.getMarketInfo(id);

			alertFail(title, "Not input Market id yet");
			return null;
		});

		if(info != null){
			MarketManageInfoManagerImpl.getInstance().setMarket(new MarketVoBuilder(info).getMarketInfo());
			return true;
		}else
			return false;
	}

	@Override
	public boolean deleteMarketInfo() {
		String title = getTitle();
		boolean valid = execute(title, () -> {
			boolean tmp = handler.deleteMarketInfo(MarketManageInfoAccessorImpl.getInstance().getMarketId());

			if (tmp == false)
				throw unknownEx();
			return tmp;
		});

		if (valid != false)
			AlertHelper.alertSuccess(title, "删除营销人员成功");
		else
			AlertHelper.alertFail(title, "删除失败！");
		MarketManageInfoManagerImpl.getInstance().setMarket(null);
		return valid != false;
	}
}
