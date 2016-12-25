package aircnc.test.service.market;

import java.util.ArrayList;
import java.util.List;

import data.dao.impl.market.MarketDaoImpl;
import data.dao.market.MarketDao;
import po.market.MarketPo;
import po.market.MarketPoBuilder;
import service.impl.market.MarketAccountManager;
import service.impl.market.MarketInfoManager;
import service.market.MarketAccountService;
import service.market.MarketInfoService;
import service.promotion.WebsitePromotionInfoService;

public class DataPrepareHelper {
	/* test data */
	private static final List<MarketPo> testData = new ArrayList<>();
	private static final String[] testName = new String[] { "AA", "BB", "CC", "DD", "EE" };
	private static final String[] testID = new String[] { "11111111", "22222222", "33333333", "44444444", "55555555" };
	private static final int testPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 5; i++) {
			MarketPoBuilder b = new MarketPoBuilder().setName(testName[i]).setID(testID[i])
					.setPasswordHash(testPass);
			testData.add(b.getMarketInfo());
		}
	}
	/* test data */

	public static final MarketDao marketDao = MarketDaoImpl.INSTANCE;
	public static final MarketAccountService accountService = MarketAccountManager.launch(marketDao);
	private static final WebsitePromotionInfoService websitePromotionInfoService = null;
	public static final MarketInfoService infoService = MarketInfoManager.launch(marketDao);

	public static final void prepareTestStatistic() {
		testData.forEach(marketDao::addMarket);
	}

	public static final void dumpTestStatistic() {
		testData.forEach(d -> marketDao.deleteMarket(d.getId()));
	}

	public static final String testID(int i) {
		return testID[i];
	}

	public static final String testName(int i) {
		return testName[i];
	}

	public static final MarketPo testData(int i) {
		return testData.get(i);
	}
}
