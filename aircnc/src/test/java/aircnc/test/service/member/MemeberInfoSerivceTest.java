package aircnc.test.service.member;

import static aircnc.test.service.member.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.testID;
import static aircnc.test.service.member.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.member.MemberAccountService;
import service.member.MemberInfoService;
import utils.info.member.MemberInfo;
import vo.member.MemberVoBuilder;

public class MemeberInfoSerivceTest {
	private MemberAccountService account = DataPrepareHelper.accountService;
	private MemberInfoService info = DataPrepareHelper.infoService;

	@Before
	public void setUp() throws Exception {
		prepareTestStatistic();
	}

	@Test
	public void testGetMemberInfo() {
		for (int i = 0; i < 5; i++) {
			MemberInfo v = info.getMemberInfo(testID(i));
			assertEquals(testName(i), v.getName());
		}
	}

	@Test
	public void testUpdateBasicInfo() {
		MemberInfo v = info.getMemberInfo(testID(3));
		account.login(v.getId(), "12345678".hashCode());
		MemberInfo v1 = new MemberVoBuilder(v).setName("BC").getMemberInfo();
		boolean result = info.updateBasicInfo(v1);
		assertEquals(true, result);
		v = info.getMemberInfo(testID(3));
		assertEquals("BC", v.getName());
	}

	@Test
	public void testUpdateAdvancedInfo() {
		MemberInfo v = info.getMemberInfo(testID(3));
		// account.login(v.getId(), "12345678".hashCode());
		MemberInfo v1 = new MemberVoBuilder(v).setEnterprise("Apple Inc.").getMemberInfo();
		boolean result = info.updateAdvancedInfo(v1);
		assertEquals(true, result);
		v = info.getMemberInfo(testID(3));
		assertEquals("Apple Inc.", v.getEnterprise());
	}

	@Test
	public void testUpdatePassword() {
		MemberInfo v = info.getMemberInfo(testID(3));
		account.login(v.getId(), "12345678".hashCode());
		// MemberInfo v1 = new MemberVoBuilder(v).setEnterprise("Apple
		// Inc.").getMemberInfo();
		boolean result = info.updatePassword("12345678".hashCode(), "123456789".hashCode());
		assertEquals(true, result);
		account.logout();
		assertEquals(true, null != account.login(v.getId(), "123456789".hashCode()));
	}

	@After
	public void tearDown() {
		dumpTestStatistic();
	}
}
