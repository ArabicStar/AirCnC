package aircnc.test.service.market;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import data.dao.MarketDao;
import data.dao.impl.MarketDaoImpl;
import service.impl.market.InfoManager;
import service.market.MarketInfoService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

public class MarketInfoServiceTest {
	private MarketInfoService info;
	private MarketDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new MarketDaoImpl();
		info = new InfoManager(dao);
	}

	@Test
	public void testGetMemberInfo() {
		MarketInfo v = info.getMarketInfo("33333333");
		assertEquals("CC", v.getName());
	}

	@Test
	public void testUpdateInfo() {
		MarketInfo v = info.getMarketInfo("22222222");
		MarketInfo v1 = new MarketVoBuilder(v).setName("BC").getMarketInfo();
		boolean result = info.updateInfo(v1);
		assertEquals(true, result);
		v = info.getMarketInfo("22222222");
		assertEquals("BC", v.getName());
	}
}
