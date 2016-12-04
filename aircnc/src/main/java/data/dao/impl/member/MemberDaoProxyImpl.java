package data.dao.impl.member;

import data.dao.member.MemberDaoProxy;
import utils.proxy.AuthenticatePolicy.Client;

public class MemberDaoProxyImpl extends MemberDaoProxy {
	private MemberDaoImpl mdi;
	private CreditDaoImpl cdi;

	protected MemberDaoProxyImpl(Client clientId) {
		super(clientId);
	}

	@Override
	public void loadMemberDao() {
		if (mdi == null)
			mdi = new MemberDaoImpl();

		loadMemberDao(mdi);
	}

	@Override
	public void loadCreditDao() {
		if (cdi == null)
			cdi = new CreditDaoImpl();

		loadCreditDao(cdi);
	}

	@Override
	public void loadCreditQueryDao() {
		if (cdi == null)
			cdi = new CreditDaoImpl();

		loadCreditQueryDao(cdi);
	}

}
