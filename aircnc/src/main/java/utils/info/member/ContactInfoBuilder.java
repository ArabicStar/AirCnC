package utils.info.member;

public abstract class ContactInfoBuilder extends ContactInfoTemplate {
	protected ContactInfoBuilder(ContactInfo info) {
		this.setEmail(info.getEmail()).setFixedPhone(info.getFixedPhone()).setMobilePhone(info.getMobilePhone());
	}

	protected ContactInfoBuilder() {
		this.setEmail(null).setFixedPhone(null).setMobilePhone(null);
	}

	protected ContactInfoBuilder setEmail(String email) {
		this.email = email == null ? BLANK : (checkEmail(email) ? email : BLANK);
		return this;
	}

	protected ContactInfoBuilder setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone == null ? BLANK : (checkFixedPhone(fixedPhone) ? fixedPhone : BLANK);
		return this;
	}

	protected ContactInfoBuilder setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? BLANK : (checkMobilePhone(mobilePhone) ? mobilePhone : BLANK);
		return this;
	}

	protected boolean isReady() {
		// blank contact info is allowed
		return (email == "" || checkEmail(email)) && (fixedPhone == "" || checkFixedPhone(fixedPhone))
				&& (mobilePhone == "" || checkMobilePhone(mobilePhone));
	}

	protected abstract ContactInfo getContactInfo();
}
