package service.impl.member;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;
import static utils.exception.StaticExceptionFactory.illegalStateException;
import java.util.Random;

import data.dao.member.MemberDao;
import po.member.MemberPo;
import po.member.MemberPoBuilder;
import service.member.MemberAccountService;
import utils.info.member.MemberInfo;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

public final class MemberAccountManager implements MemberAccountService {
	private static MemberAccountService instance;

	public static MemberAccountService launch(MemberDao dao) {
		if (instance != null)
			throw duplicateSingletonEx();

		instance = new MemberAccountManager(dao);
		return instance;
	}

	public static MemberAccountService getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

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

	private MemberAccountManager(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public MemberInfo register(MemberVoBuilder newMemberInfo, int passwordHash) {
		if (newMemberInfo == null)
			return null;

		String newID = generateNewID();// generate a new id

		// set id and password, build po
		MemberVo newMemberVo = newMemberInfo.setId(newID).getMemberInfo();
		MemberPo newMemberPo = new MemberPoBuilder(newMemberVo).setPasswordHash(passwordHash).getMemberInfo();

		// add new member
		boolean result = dao.addMember(newMemberPo);
		if (result)
			return newMemberVo;

		return MemberVoBuilder.invalidInfo();
	}

	/* generate a never-used id */
	private String generateNewID() {
		// random generate
		String stringID = MemberInfo.formatID(R.nextInt(ID_BOUND));

		// check and generate loop
		for (int numId = R.nextInt(ID_BOUND); dao.existsMember(stringID); numId = R.nextInt(ID_BOUND))
			stringID = MemberInfo.formatID(numId);

		return stringID;
	}

	@Override
	public MemberInfo login(String id, int passwordHash) {
		if (!MemberInfo.checkID(id))
			return null;

		MemberPo memberAccount;
		memberAccount = dao.findMember(id);

		if (memberAccount == null)// not exist
			return null;

		// password verify
		if (!checkPassword(memberAccount, passwordHash))
			return MemberVoBuilder.invalidInfo();

		// set login status
		this.isLogined = true;
		this.currentAccount = memberAccount;
		return currentAccount;
	}

	@Override
	public boolean logout() {
		if (!isLoggedin())
			throw illegalStateException("Not login yet");

		// set logout status
		this.isLogined = false;
		this.currentAccount = null;
		return true;
	}

	@Override
	public boolean isLoggedin() {
		return isLogined;
	}

	@Override
	public MemberPo getCurrentAccount() {
		return currentAccount;
	}

	@Override
	public boolean existsMember(String id) {
		if (!MemberInfo.checkID(id))
			return false;

		return dao.existsMember(id);
	}

	private static boolean checkPassword(MemberPo po, int passwordHash) {
		return po.getPasswordHash() == passwordHash;
	}

	@Override
	public MemberInfo refreshCurrentAccount() {
		return currentAccount = dao.findMember(currentAccount.getId());
	}
}
