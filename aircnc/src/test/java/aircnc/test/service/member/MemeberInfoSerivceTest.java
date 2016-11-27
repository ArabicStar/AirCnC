package aircnc.test.service.member;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.dao.impl.member.MemberDaoImpl;
import data.dao.member.MemberDao;
import javafx.util.converter.LocalDateStringConverter;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.impl.member.MemberAccountManager;
import service.impl.member.MemberInfoManager;
import service.member.MemberAccountService;
import service.member.MemberInfoService;
import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

public class MemeberInfoSerivceTest {
	/* test data */
	private static final List<MemberPo> testData = new ArrayList<>();
	private static final String[] testType = new String[] { "Personal", "Personal", "Personal", "Business",
			"Business" };
	private static final ContactInfo c = new ContactVoBuilder().getContactInfo();
	private static final int[] testCredit = new int[] { 100, 1000, 999, 8, 29102784 };
	private static final String[] testName = new String[] { "AA", "BB", "CC", "DD", "EE" };
	private static final String[] testID = new String[] { "11111111", "22222222", "33333333", "44444444", "55555555" };
	private static final String[] testExtra = new String[] { "1998-01-01", "1973-02-11", "2000-01-12",
			"Microsoft Ltd,.Co.", "Apple Inc." };
	private static final int testPass = "12345678".hashCode();
	static {
		for (int i = 0; i < 5; i++) {
			MemberPoBuilder b = new MemberPoBuilder(testType[i]).setName(testName[i]).setContactInfo(c)
					.setCredit(testCredit[i]).setId(testID[i]).setEnterprise(testExtra[i]).setPasswordHash(testPass);
			if (i <= 2)
				b.setBirthday(new LocalDateStringConverter().fromString(testExtra[i]));
			testData.add(b.getMemberInfo());
		}
	}
	/* test data */
	private MemberInfoService info;
	private MemberAccountService account;
	private MemberDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new MemberDaoImpl();
		account = new MemberAccountManager(dao);
		info = new MemberInfoManager(account, dao, null);
		prepareTestStatistic();
	}

	private void prepareTestStatistic() {
		testData.forEach(dao::addMember);
		account.login(testID[0], testPass);
	}

	@Test
	public void testGetMemberInfo() {
		for (int i = 0; i < testID.length; i++) {
			MemberInfo v = info.getMemberInfo(testID[i]);
			assertEquals(testName[i], v.getName());
		}
	}

	@Test
	public void testUpdateInfo()  {
		MemberInfo v = info.getMemberInfo(testID[0]);
		MemberInfo v1 = new MemberVoBuilder(v).setName("BC").getMemberInfo();
		boolean result = info.updateInfo(v1);
		assertEquals(true, result);
		v = info.getMemberInfo(testID[0]);
		assertEquals("BC", v.getName());
	}

	@After
	public void tearDown() {
		testData.forEach(d -> dao.deleteMember(d.getId()));
	}
}
