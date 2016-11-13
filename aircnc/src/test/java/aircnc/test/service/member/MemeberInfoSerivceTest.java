package aircnc.test.service.member;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import data.dao.MemberDao;
import data.dao.impl.MemberDaoImpl;
import service.impl.member.InfoManager;
import service.member.MemberInfoService;
import utils.info.member.MemberInfo;
import vo.member.MemberVoBuilder;

public class MemeberInfoSerivceTest {
	private MemberInfoService info;
	private MemberDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new MemberDaoImpl();
		info = new InfoManager(dao, null, null);
	}

	@Test
	public void testGetMemberInfo() {
		MemberInfo v = info.getMemberInfo("33333333");
		assertEquals("CC", v.getName());
	}

	@Test
	public void testUpdateInfo() {
		MemberInfo v = info.getMemberInfo("22222222");
		MemberInfo v1 = new MemberVoBuilder(v).setName("BC").getMemberInfo();
		boolean result = info.updateInfo(v1);
		assertEquals(true, result);
		v = info.getMemberInfo("22222222");
		assertEquals("BC", v.getName());
	}

}
