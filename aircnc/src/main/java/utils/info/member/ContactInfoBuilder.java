package utils.info.member;

/**
 * Builder for ContactVo, to assist to assure immutable object <br>
 * Setters will be checked <br>
 * P.S. the ContactVoBuilder and ContactPoBuilder perform just the same<br>
 * 
 * @author ClevelandAlto
 *
 */
public abstract class ContactInfoBuilder extends ContactInfoTemplate {
	protected ContactInfoBuilder(ContactInfo info) {
		this.setEmail(info.getEmail()).setFixedPhone(info.getFixedPhone()).setMobilePhone(info.getMobilePhone());
	}

	protected ContactInfoBuilder() {
		this.setEmail(null).setFixedPhone(null).setMobilePhone(null);
	}

	public ContactInfoBuilder setEmail(String email) {
		this.email = email == null ? BLANK : (checkEmail(email) ? email : BLANK);
		return this;
	}

	public ContactInfoBuilder setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone == null ? BLANK : (checkFixedPhone(fixedPhone) ? fixedPhone : BLANK);
		return this;
	}

	public ContactInfoBuilder setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? BLANK : (checkMobilePhone(mobilePhone) ? mobilePhone : BLANK);
		return this;
	}

	public boolean isReady() {
		// blank contact info is allowed
		return (email == "" || checkEmail(email)) && (fixedPhone == "" || checkFixedPhone(fixedPhone))
				&& (mobilePhone == "" || checkMobilePhone(mobilePhone));
	}

	public abstract ContactInfo getContactInfo();
}
