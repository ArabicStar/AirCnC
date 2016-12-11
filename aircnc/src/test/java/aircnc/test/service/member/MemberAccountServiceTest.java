package aircnc.test.service.member;

import static aircnc.test.service.member.DataPrepareHelper.dumpTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.prepareTestStatistic;
import static aircnc.test.service.member.DataPrepareHelper.testID;
import static aircnc.test.service.member.DataPrepareHelper.testName;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Test;

import service.member.MemberAccountService;
import utils.info.member.MemberInfo;
import vo.member.ContactVoBuilder;
import vo.member.MemberVoBuilder;

public class MemberAccountServiceTest {
	private MemberAccountService acc = DataPrepareHelper.accountService;

	private static final int idx = 3;
	private String registeredId;

	public MemberAccountServiceTest() {
		prepareTestStatistic();
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
		MemberInfo v = acc.login(testID(idx), "12345678".hashCode());
		assertEquals(testName(idx), v.getName());
		assertEquals(testName(idx), acc.getCurrentAccount().getName());
		assertEquals(true, acc.isLoggedin());
	}

	@Test
	public void testLogout() {
		assertEquals(true, acc.logout());
		assertEquals(false, acc.isLoggedin());
	}

	@Test
	public void testExistsMember() {
		boolean res1 = false, res2 = false;
		try {
			res1 = acc.existsMember(testID(idx));
			res2 = acc.existsMember("12345678");
			res2 = acc.existsMember("1234578");
		} catch (Exception e) {
		}
		assertEquals(true, res1);
		assertEquals(false, res2);
	}

	@After
	public void tearDown() {
		dumpTestStatistic();
		try {
			DataPrepareHelper.memberDao.deleteMember(registeredId);
		} catch (Exception e) {
		}
	}
}
