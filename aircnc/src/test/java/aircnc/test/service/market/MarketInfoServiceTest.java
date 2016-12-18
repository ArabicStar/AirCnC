package aircnc.test.service.market;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.impl.market.MarketDaoImpl;
import data.dao.market.MarketDao;
import service.market.MarketAccountService;
//import service.impl.market.InfoManager;
import service.market.MarketInfoService;
import utils.info.market.MarketInfo;
import vo.market.MarketVoBuilder;

public class MarketInfoServiceTest {
//	private MarketAccountService account = DataPrepareHelper.accountService;
//	private MarketInfoService info = DataPrepareHelper.infoService;
//
//	@Before
//	public void setUp() throws Exception {
//		prepareTestStatistic();
//	}
//
//	@Test
//	public void testGetMemberInfo() {
//		for (int i = 0; i < 5; i++) {
//			MarketInfo v = info.getMemberInfo(testID(i));
//			assertEquals(testName(i), v.getName());
//		}
//	}
//
//	@Test
//	public void testUpdateBasicInfo() {
//		MemberInfo v = info.getMemberInfo(testID(3));
//		account.login(v.getId(), "12345678".hashCode());
//		MemberInfo v1 = new MemberVoBuilder(v).setName("BC").getMemberInfo();
//		boolean result = info.updateBasicInfo(v1);
//		assertEquals(true, result);
//		v = info.getMemberInfo(testID(3));
//		assertEquals("BC", v.getName());
//	}
//
//	@Test
//	public void testUpdateAdvancedInfo() {
//		MemberInfo v = info.getMemberInfo(testID(3));
//		// account.login(v.getId(), "12345678".hashCode());
//		MemberInfo v1 = new MemberVoBuilder(v).setEnterprise("Apple Inc.").getMemberInfo();
//		boolean result = info.updateAdvancedInfo(v1);
//		assertEquals(true, result);
//		v = info.getMemberInfo(testID(3));
//		assertEquals("Apple Inc.", v.getEnterprise());
//	}
//
//	@Test
//	public void testUpdatePassword() {
//		MemberInfo v = info.getMemberInfo(testID(3));
//		account.login(v.getId(), "12345678".hashCode());
//		// MemberInfo v1 = new MemberVoBuilder(v).setEnterprise("Apple
//		// Inc.").getMemberInfo();
//		boolean result = info.updatePassword("12345678".hashCode(), "123456789".hashCode());
//		assertEquals(true, result);
//		account.logout();
//		assertEquals(true, null != account.login(v.getId(), "123456789".hashCode()));
//	}
//
//	@After
//	public void tearDown() {
//		dumpTestStatistic();
//	}
}
