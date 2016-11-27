package data.dao.impl.member;

import data.dao.member.MemberDaoAccess;
import data.dao.member.MemberDaoProxy;

public class MemberDaoProxyImpl extends MemberDaoProxy {

	public MemberDaoProxyImpl(MemberDaoAccess access) {
		super(access);
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
