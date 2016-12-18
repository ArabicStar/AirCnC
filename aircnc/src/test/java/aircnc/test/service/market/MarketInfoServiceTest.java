package aircnc.test.service.market;

import static aircnc.test.service.market.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.market.DataPrepareHelper.testID;
import static aircnc.test.service.market.DataPrepareHelper.testName;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.market.MarketInfoService;
import utils.info.market.MarketInfo;
import vo.promotion.PromotionVo;

public class MarketInfoServiceTest {
	private MarketInfoService info = DataPrepareHelper.infoService;

	@Before
	public void setUp() throws Exception {
		prepareTestStatistic();
	}

	@Test
	public void testGetMarketInfo() {
		for (int i = 0; i < 5; i++) {
			MarketInfo v = info.getMarketInfo(testID(i));
			assertEquals(testName(i), v.getName());
		}
	}

//	@Test
//	public void testGetWebSitePromotion() {
//		Set<PromotionVo> vo = info.getMarketPromotion();
//		
//		assertEquals(true, null == vo);
//	}

	@After
	public void tearDown() {
		dumpTestStatistic();
	}
}
