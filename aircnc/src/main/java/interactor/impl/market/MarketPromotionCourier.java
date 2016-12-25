package interactor.impl.market;

import static interactor.utils.AlertHelper.alertFail;
import static interactor.utils.AlertHelper.alertSuccess;
import static interactor.utils.Dipatcher.execute;
import static interactor.utils.TitleGetter.getTitle;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Set;

import interactor.market.MarketPromotionInteractor;
import interactor.utils.Title;
import presentation.market.accessor.impl.MarketPromotionAccessorImpl;
import presentation.market.manage.impl.MarketPromotionManagerImpl;
import service.market.MarketAccountService;
import service.promotion.WebsitePromotionManagementService;
import utils.info.market.MarketInfo;
import vo.promotion.PromotionVo;

public class MarketPromotionCourier implements MarketPromotionInteractor{
	private static MarketPromotionInteractor instance;

	public static MarketPromotionInteractor launch(WebsitePromotionManagementService handler,MarketAccountService helper) {
		/* singleton */
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new MarketPromotionCourier(handler,helper);
	}

	public static MarketPromotionInteractor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	/* singleton */

	private WebsitePromotionManagementService handler;
	private MarketAccountService helper;

	private MarketPromotionCourier(WebsitePromotionManagementService handler,MarketAccountService helper) {
		this.handler = handler;
		this.helper = helper;
	}

	@Override
	@Title("更新促销策略")
	public void update() {
		String title = getTitle();

		execute(title, () -> {
			String id = getCurrentId();
			if (id != null)

				if (!handler.updateWebsitePromotion(MarketPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "修改网站促销策略失败");
				else
					alertSuccess(title, "修改网站促销策略成功");
			return null;
		});
	}

	@Override
	@Title("新增促销策略")
	public void addNew() {
		String title = getTitle();

		execute(title, () -> {
			String id = getCurrentId();
			if (id != null)

				if (!handler.addWebsitePromotion(MarketPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "新增网站促销策略失败");
				else
					alertSuccess(title, "新增网站促销策略成功");
			return null;
		});
	}

	@Override
	@Title("删除促销策略")
	public void delete() {
		String title = getTitle();

		execute(title, () -> {
			String id = getCurrentId();
			if (id != null)

				if (!handler.deleteWebsitePromotion(MarketPromotionAccessorImpl.getInstance().getPromotion()))
					alertFail(title, "删除网站促销策略失败");
				else
					alertSuccess(title, "删除网站促销策略成功");
			return null;
		});
	}

	@Override
	@Title("获取促销策略")
	public boolean getMarketActivePromotion() {
		String title = getTitle();
		Set<PromotionVo> promotions = execute(title, () -> {
			String id = getCurrentId();
			if (id != null)
				return handler.getWebsiteActivePromotion();

			alertFail(title, "还没登陆呢");
			return null;
		});
		
		MarketPromotionManagerImpl.getInstance().setPromotion(promotions);
		return false;
	}
	
	private String getCurrentId() {
		MarketInfo curAcc = helper.getCurrentAccount();
		return curAcc == null ? null: curAcc.getId();
	}
}
