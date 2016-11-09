package utils.info.member.credit;

import java.time.Instant;

public abstract class CreditChangeInfo extends CreditChangeTemplate {
	public String getMemberID() {
		return memberID;
	}

	public Instant getTimeInstant() {
		return timeInstant;
	}

	public int getBeforeCredit() {
		return beforeCredit;
	}

	public int getAfterCredit() {
		return afterCredit;
	}

	public String getFormatString() {
		return new StringBuilder(action.getFormatString()).append(STRING_CACHE).toString();
	}
}
