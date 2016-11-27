package service.impl.member;

import data.dao.member.MemberDaoProxy;
import service.member.MemberServiceAccess;
import service.member.MemberServiceProxy;

public class MemberServiceProxyImpl extends MemberServiceProxy {

	public MemberServiceProxyImpl(MemberServiceAccess access, MemberDaoProxy dao) {
		super(access, dao);
	}

	@Override
	public void loadAccountService() {
		loadAccountService(new MemberAccountManager(dao));

	}

	// TODO
	@Override
	public void loadInfoService() {
		loadInfoService(new MemberInfoManager(this, dao, null));
	}

	@Override
	public void loadCreditService() {
		loadCreditService(new MemberCreditManager(dao, dao));
	}

}
