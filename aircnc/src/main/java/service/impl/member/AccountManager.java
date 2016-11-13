package service.impl.member;

import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import utils.info.member.MemberInfo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public final class AccountManager implements MemberAccountService {
	private MemberDao dao;

	private boolean isLogined = false;
	private MemberVo loginedMember = null;

	public AccountManager(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public MemberVo register(MemberVoBuilder newMemberInfo, int passwordHash) {
		String newID = generateNewID();
		MemberVo newMemberVo = newMemberInfo.setID(newID).getMemberInfo();
		MemberPo newMemberPo = new MemberPoBuilder(newMemberVo).setPasswordHash(passwordHash).getMemberInfo();
		System.out.println(newMemberPo);
		boolean result = dao.addMember(newMemberPo);
		if (result)
			return newMemberVo;

		return MemberVoBuilder.getInvalidInfo();
	}

	// generate a never-used id
	private String generateNewID() {
		return MemberInfo.formatID(dao.getAvaliableID());
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
//		System.out.println(loginedMember.getID());
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
