package service.impl.member;

import data.dao.member.MemberDaoProxy;
import service.member.MemberServiceProxy;
import utils.proxy.AuthenticatePolicy.Client;

public class MemberServiceProxyImpl extends MemberServiceProxy {

	public MemberServiceProxyImpl(Client clientId, MemberDaoProxy dao) {
		super(clientId, dao);
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
		loadCreditService(new MemberCreditManager(dao, dao,dao));
	}

}
