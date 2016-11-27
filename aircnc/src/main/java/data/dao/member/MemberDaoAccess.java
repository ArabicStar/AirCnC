package data.dao.member;

public enum MemberDaoAccess {

	USER(true, false), HOTEL(false, true), MARKET(false, true), MANAGE(false, false);

	private boolean memberDaoAccess;
	private boolean creditDaoAccess;

	MemberDaoAccess(boolean memberDaoAccess, boolean creditDaoAccess) {
		this.memberDaoAccess = memberDaoAccess;
		this.creditDaoAccess = creditDaoAccess;
	}

	public boolean isCreditDaoAccessed() {
		return creditDaoAccess;
	}

	public boolean isMemberDaoAccessed() {
		return memberDaoAccess;
	}
}
