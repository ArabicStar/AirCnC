package data.dao.impl.member;

import data.dao.member.MemberDaoProxy;
import utils.proxy.AuthenticatePolicy.Client;

public class MemberDaoProxyImpl extends MemberDaoProxy {

	protected MemberDaoProxyImpl(Client clientId) {
		super(clientId);
	}

	@Override
	public void loadMemberDao() {
		loadMemberDao(new MemberDaoImpl());
	}

	@Override
	public void loadCreditDao() {
		loadCreditDao(new CreditDaoImpl());
	}

}
