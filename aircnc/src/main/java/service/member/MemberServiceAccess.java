package service.member;

public enum MemberServiceAccess {
	USER(true, true, false), HOTEL(false, false, true), MARKET(false, false, true), MANAGE(false, false, false);

	private boolean accountServiceAccess;
	private boolean infoServiceAccess;
	private boolean creditServiceAccess;

	MemberServiceAccess(boolean accountServiceAccess, boolean infoServiceAccess, boolean creditServiceAccess) {
		this.accountServiceAccess = accountServiceAccess;
		this.infoServiceAccess = infoServiceAccess;
		this.creditServiceAccess = creditServiceAccess;
	}

	public boolean isAccountServiceAccessed() {
		return accountServiceAccess;
	}

	public boolean isCreditServiceAccessed() {
		return creditServiceAccess;
	}

	public boolean isInfoServiceAccessed() {
		return infoServiceAccess;
	}
}
