package aircnc.test.service.member;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import data.dao.MemberDao;
import data.dao.impl.MemberDaoImpl;
import service.impl.member.AccountManager;
import service.member.MemberAccountService;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public class MemberAccountServiceTest {
	private MemberAccountService acc;
	private MemberDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new MemberDaoImpl();
		acc = new AccountManager(dao);
	}

	@Test
	public void testRegister() {
		MemberVoBuilder b = new MemberVoBuilder("Personal").setBirthday(LocalDate.parse("1998-04-17"))
				.setContactInfo(new ContactVoBuilder().getContactInfo()).setCredit(0).setName("FF");
		MemberVo v = acc.register(b, "12345678".hashCode());
		assertEquals("10000000", v.getID());
		assertEquals(true, acc.existsMember(v.getID()));
	}

	@Test
	public void testLogin() {
		MemberVo v = acc.login("11111111", "12345678".hashCode());
		assertEquals("AA", v.getName());
		assertEquals("AA", acc.getLoginedMember().getName());
		assertEquals(true, acc.isLogined());
	}

	@Test
	public void testLogout() {
		assertEquals(true, acc.logout());
		assertEquals(false, acc.isLogined());
	}

	@Test
	public void testExistsMember() {
		boolean res1, res2;
		res1 = acc.existsMember("22222222");
		res2 = acc.existsMember("12111111");
		assertEquals(true, res1);
		assertEquals(false, res2);
	}

}
