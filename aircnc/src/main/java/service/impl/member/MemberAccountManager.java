package service.impl.member;

import java.util.Random;

import data.dao.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import utils.info.member.MemberInfo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public final class MemberAccountManager implements MemberAccountService {
	private static final int ID_BOUND = 100000000;
	private static final Random R = new Random(System.currentTimeMillis());

	private MemberDao dao;

	private boolean isLogined = false;
	/**
	 * Logined member info.<br>
	 * Actually, it should be a MemberPo instance, so casts it when neccessary.
	 * <br>
	 */
	private MemberPo currentAccount = null;

	public MemberAccountManager(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public MemberInfo register(MemberVoBuilder newMemberInfo, int passwordHash) {
		String newID = generateNewID();

		MemberVo newMemberVo = newMemberInfo.setId(newID).getMemberInfo();
		MemberPo newMemberPo = new MemberPoBuilder(newMemberVo).setPasswordHash(passwordHash).getMemberInfo();

		// System.out.println(newMemberPo);
		boolean result = dao.addMember(newMemberPo);
		System.out.println(result);
		if (result)
			return newMemberVo;

		return MemberVoBuilder.getInvalidInfo();
	}

	/* generate a never-used id */
	private String generateNewID() {
		String stringID = MemberInfo.formatID(R.nextInt(ID_BOUND));
		for (int numId = R.nextInt(ID_BOUND); dao.existsMember(stringID); numId = R.nextInt(ID_BOUND))
			stringID = MemberInfo.formatID(numId);

		return stringID;
	}

	@Override
	public MemberInfo login(String id, int passwordHash) {
		MemberPo memberAccount = dao.findMember(id);

		if (memberAccount == null)
			return null;

		if (!checkPassword(memberAccount, passwordHash))
			return MemberVoBuilder.getInvalidInfo();

		this.isLogined = true;
		this.currentAccount = memberAccount;
		// System.out.println(loginedMember.getID());
		return currentAccount;
	}

	@Override
	public boolean logout() {
		this.isLogined = false;
		this.currentAccount = null;
		return true;
	}

	@Override
	public boolean isLogined() {
		return isLogined;
	}

	@Override
	public MemberPo getCurrentAccount() {
		return currentAccount;
	}

	@Override
	public boolean existsMember(String id) {
		return dao.existsMember(id);
	}

	private static boolean checkPassword(MemberPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}

	@Override
	public void refreshCurrentAccount() {
		currentAccount = dao.findMember(currentAccount.getId());
	}
}
