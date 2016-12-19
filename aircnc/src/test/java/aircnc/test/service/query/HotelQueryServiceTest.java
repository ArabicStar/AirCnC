package aircnc.test.service.query;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.query.HotelQueryService;

public class HotelQueryServiceTest {
	private HotelQueryService hqs = DataPrepareHelper.hqs;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() {
		assertNull(hqs.findById(1));
	}

	@Test
	public void testFindByName() {
		assertNull(hqs.findByName("a"));
	}

	@Test
	public void testFindByCondition() {
		assertNull(hqs.findByCondition(null));
	}

}
