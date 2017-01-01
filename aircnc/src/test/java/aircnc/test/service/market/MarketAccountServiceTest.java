package aircnc.test.service.market;

import static aircnc.test.service.market.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.testID;
import static aircnc.test.service.market.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import data.dao.market.MarketDao;
import service.market.MarketAccountService;
import utils.info.level.LevelStrategy;
import utils.info.market.MarketInfo;

public class MarketAccountServiceTest {
	private MarketAccountService acc = DataPrepareHelper.accountService;
	private MarketDao marDao = DataPrepareHelper.marketDao;

	private static final int idx = 3;
	private String registeredId;
	
	@Test
	public void testLogin() {
		LevelStrategy ls = new LevelStrategy();
		ls.setThreshold(1,100);
		ls.setThreshold(2,200);
		ls.setThreshold(3,300);
		ls.setThreshold(4,400);
		ls.setThreshold(5,500);
		ls.setThreshold(6,600);
		ls.setThreshold(7,700);
		ls.setThreshold(8,800);
		ls.setThreshold(9,900);
		ls.setThreshold(10,1000);
		marDao.updateLevelStrategy(ls);
	}

//	public MarketAccountServiceTest() {
//		prepareTestStatistic();
//	}
//
//	@Test
//	public void testLogin() {
//		MarketInfo v = acc.login(testID(idx), "12345678".hashCode());
//		assertEquals(testName(idx), v.getName());
//		assertEquals(testName(idx), acc.getCurrentAccount().getName());
//		assertEquals(true, acc.isLoggedin());
//	}
//
//	@Test
//	public void testLogout() {
//		assertEquals(true, acc.logout());
//		assertEquals(false, acc.isLoggedin());
//	}
//
//	@Test
//	public void testExistsMarket() {
//		boolean res1 = false, res2 = false;
//		try {
//			res1 = acc.existsMarket(testID(idx));
//			res2 = acc.existsMarket("12345678");
//			res2 = acc.existsMarket("1234578");
//		} catch (Exception e) {
//		}
//		assertEquals(true, res1);
//		assertEquals(false, res2);
//	}
//
//	@After
//	public void tearDown() {
//		dumpTestStatistic();
//		try {
//			DataPrepareHelper.marketDao.deleteMarket(registeredId);
//		} catch (Exception e) {
//		}
//	}
}
