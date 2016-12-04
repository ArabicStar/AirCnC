package aircnc.test.service.member;

import static aircnc.test.service.member.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.testID;
import static aircnc.test.service.member.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.MemberDao;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberInfoManager;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import utils.info.member.MemberInfo;
import vo.member.MemberVoBuilder;

public class MemeberInfoSerivceTest {
	private MemberInfoService info;
	private MemberAccountService account;
	private MemberDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new MemberDaoImpl();
		account = new MemberAccountManager(dao);
		info = new MemberInfoManager(account, dao, null);
		prepareTestStatistic(dao);
	}

	@Test
	public void testGetMemberInfo() {
		for (int i = 0; i < 5; i++) {
			MemberInfo v = info.getMemberInfo(testID(i));
			assertEquals(testName(i), v.getName());
		}
	}

	@Test
	public void testUpdateInfo() {
		MemberInfo v = info.getMemberInfo(testID(3));
		account.login(v.getId(), "12345678".hashCode());
		MemberInfo v1 = new MemberVoBuilder(v).setName("BC").getMemberInfo();
		boolean result = info.updateInfo(v1);
		assertEquals(true, result);
		v = info.getMemberInfo(testID(3));
		assertEquals("BC", v.getName());
	}

	@After
	public void tearDown() {
		dumpTestStatistic(dao);
	}
}
