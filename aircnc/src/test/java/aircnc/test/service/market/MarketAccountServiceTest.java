package aircnc.test.service.market;

import static aircnc.test.service.market.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.testID;
import static aircnc.test.service.market.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import service.market.MarketAccountService;
import utils.info.market.MarketInfo;

public class MarketAccountServiceTest {
	private MarketAccountService acc = DataPrepareHelper.accountService;

	private static final int idx = 3;
	private String registeredId;

	public MarketAccountServiceTest() {
		prepareTestStatistic();
	}

	@Test
	public void testLogin() {
		MarketInfo v = acc.login(testID(idx), "12345678".hashCode());
		assertEquals(testName(idx), v.getName());
		assertEquals(testName(idx), acc.getCurrentAccount().getName());
		assertEquals(true, acc.isLoggedin());
	}

	@Test
	public void testLogout() {
		assertEquals(true, acc.logout());
		assertEquals(false, acc.isLoggedin());
	}

	@Test
	public void testExistsMarket() {
		boolean res1 = false, res2 = false;
		try {
			res1 = acc.existsMarket(testID(idx));
			res2 = acc.existsMarket("12345678");
			res2 = acc.existsMarket("1234578");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}

	@After
	public void tearDown() {
		dumpTestStatistic();
		try {
			DataPrepareHelper.marketDao.deleteMarket(registeredId);
		} catch (Exception e) {
		}
	}
}
