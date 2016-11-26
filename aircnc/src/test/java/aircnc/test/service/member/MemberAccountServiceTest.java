package aircnc.test.service.member;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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
import service.member.MemberAccountService;
import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

public class MemberAccountServiceTest {
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
	private MemberAccountService acc;
	private MemberDao dao;

	private int idx = 3;
	private String registeredId;

	@Before
	public void setUp() throws Exception {
		dao = new MemberDaoImpl();
		acc = new MemberAccountManager(dao);
		prepareTestStatistic();
	}

	private void prepareTestStatistic() {
		testData.forEach(dao::addMember);
	}

	@Test
	public void testRegister() {
		MemberVoBuilder b = new MemberVoBuilder("Personal").setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().setEmail("12345@qq.com").getContactInfo()).setCredit(0)
				.setName("FF");
		MemberInfo v = acc.register(b, "12345678".hashCode());
		registeredId = v.getId();
		assertEquals(true, acc.existsMember(registeredId));
	}

	@Test
	public void testLogin() {
		MemberInfo v = acc.login(testID[idx], "12345678".hashCode());
		assertEquals(testName[idx], v.getName());
		assertEquals(testName[idx], acc.getCurrentAccount().getName());
		assertEquals(true, acc.isLogined());
	}

	@Test
	public void testLogout() {
		assertEquals(true, acc.logout());
		assertEquals(false, acc.isLogined());
	}

	@Test
	public void testExistsMember() {
		boolean res1 = false, res2 = false;
		try {
			res1 = acc.existsMember(testID[idx]);
			res2 = acc.existsMember("12345678");
			res2 = acc.existsMember("1234578");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}

	@After
	public void tearDown() {
		testData.forEach(d -> dao.deleteMember(d.getId()));
		try {
			dao.deleteMember(registeredId);
		} catch (Exception e) {
		}
	}
}
