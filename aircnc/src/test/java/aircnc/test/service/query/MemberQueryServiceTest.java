package aircnc.test.service.query;

import static aircnc.test.service.query.DataPrepareHelper.testID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.query.MemberQueryService;

public class MemberQueryServiceTest {
	private MemberQueryService mqs = DataPrepareHelper.mqs;

	@Before
	public void setUp() throws Exception {
		DataPrepareHelper.prepareTestStatistic();
	}

	@After
	public void tearDown() throws Exception {
		DataPrepareHelper.dumpTestStatistic();
	}

	@Test
	public void testSearchById() {
		assertNull(mqs.searchById("00000001"));
		assertEquals("AA", mqs.searchById(testID(0)).getName());
	}

}
