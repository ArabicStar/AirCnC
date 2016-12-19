package aircnc.test.service.query;

import static aircnc.test.service.query.DataPrepareHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.query.CreditQueryService;

public class CreditQueryServiceTest {
	private CreditQueryService cqs = DataPrepareHelper.cqs;

	@Before
	public void setUp() throws Exception {
		DataPrepareHelper.prepareTestStatistic();
	}

	@After
	public void tearDown() throws Exception {
		DataPrepareHelper.dumpTestStatistic();
	}

	@Test
	public void testGetMemberCredit() {
		assertEquals(Integer.MIN_VALUE, cqs.getMemberCredit("00000001"));
		assertEquals(100, cqs.getMemberCredit(testID(0)));
	}

	@Test
	public void testSearchByMemberId() {
		assertNull(cqs.searchByMemberId("00000001"));
		assertEquals(6, cqs.searchByMemberId(testID(0)).size());
	}

}
