package service.impl.member;

import java.util.Random;

import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public final class AccountManager implements MemberAccountService {
	private static final Random NEW_ID_GENERATOR = new Random(System.currentTimeMillis());
	private static final int ID_BOUND = 100000000;
	private MemberDao dao;

	private boolean isLogined = false;
	private MemberVo loginedMember = null;

	@Override
	public MemberVo register(MemberVoBuilder newMemberInfo, int passwordHash) {
		String newID = generateNewID();
		MemberVo newMemberVo = newMemberInfo.setID(newID).getMemberInfo();
		MemberPo newMemberPo = new MemberPoBuilder(newMemberVo).setPasswordHash(passwordHash).getMemberInfo();
		boolean result = dao.addMember(newMemberPo);
		if (result)
			return newMemberVo;

		return MemberVoBuilder.getInvalidInfo();
	}

	// generate a never-used id
	private String generateNewID() {
		int i = NEW_ID_GENERATOR.nextInt(ID_BOUND);
		String newID;
		while (dao.existsMember(newID = formatID(i)))
			i = NEW_ID_GENERATOR.nextInt(ID_BOUND);

		return newID;
	}

	// format an id
	private static String formatID(int i) {
		return String.format("%08d", i);
	}

	@Override
	public MemberVo login(String id, int passwordHash) {
		if (!existsMember(id))
			return null;

		MemberPo memberAccount = dao.findMember(id);
		if (!checkPassword(memberAccount, passwordHash))
			return MemberVoBuilder.getInvalidInfo();

		this.isLogined = true;
		this.loginedMember = new MemberVoBuilder(memberAccount).getMemberInfo();
		return loginedMember;
	}

	@Override
	public boolean logout() {
		this.isLogined = false;
		this.loginedMember = null;
		return true;
	}

	@Override
	public boolean isLogined() {
		return isLogined;
	}

	@Override
	public MemberVo getLoginedMember() {
		return loginedMember;
	}

	@Override
	public boolean existsMember(String id) {
		return dao.existsMember(id);
	}

	private static boolean checkPassword(MemberPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}
}
