package utils.info.member;

import java.time.LocalDate;

/**
 * Abstract of builder for MemberInfo, assisting to assure immutable object.
 * <br>
 * <br>
 * <b>NOTICE:</b>
 * <ul>
 * <li>Default credit value is 0. Other field have <b>NO</b> default value. All
 * field should be set before build a MemberInfo instance.<br>
 * <li>All parameters of setter will be checked firstly. If invalid, the setting
 * will not take effect, and IllgealArgumentException will be thrown. <br>
 * </ul>
 * 
 * @author ClevelandAlto
 * @see utils.info.member.MemberInfo
 */
public abstract class MemberInfoBuilder extends MemberInfoTemplate {
	/**
	 * Initialize a builder by given type. <br>
	 * 
	 * @param type
	 */
	public MemberInfoBuilder(String type) {
		if (type == null)
			throw new IllegalArgumentException("Null Type String");

		this.type = Type.valueOf(type.toUpperCase());
		if (this.type == null)
			throw new IllegalArgumentException("Invalid Type String");

		credit = 0;
	}

	/**
	 * Initialize a builder by given MemberInfo, all information will be kept.
	 * <br>
	 * 
	 * @param info
	 */
	public MemberInfoBuilder(MemberInfo info) {
		this(info.getType());
		this.setId(info.getId()).setName(info.getName()).setContactInfo(info.getContact()).setCredit(info.getCredit())
				.setEnterprise(info.getEnterprise()).setBirthday(info.getBirthday());
	}

	/**
	 * Set id. <br>
	 * 
	 * @param id
	 *            id string <br>
	 * @return this instance <br>
	 */
	public MemberInfoBuilder setId(String id) {
		if (checkID(id))
			this.id = id;
		else
			throw new IllegalArgumentException("Wrong ID");
		return this;
	}

	/**
	 * Set name. <br>
	 * 
	 * @param name
	 *            name string <br>
	 * @return this instance <br>
	 */
	public abstract MemberInfoBuilder setName(String name);

	/**
	 * Set contact. <br>
	 * 
	 * @param contact
	 *            ContactInfo instance <br>
	 * @return this instance <br>
	 */
	public MemberInfoBuilder setContactInfo(ContactInfo contact) {
		if (contact != null)
			this.contact = contact;
		else
			throw new IllegalArgumentException("Null Contact info");
		return this;
	}

	/**
	 * Set credit. <br>
	 * 
	 * @param credit
	 *            credit integer <br>
	 * @return this instance <br>
	 */
	public MemberInfoBuilder setCredit(int credit) {
		if (credit != WRONG_CREDIT)
			this.credit = credit;
		else
			throw new IllegalArgumentException("Wrong credit value");
		return this;
	}

	/**
	 * Set enterprise. <br>
	 * 
	 * @param enterprise
	 *            enterprise name string <br>
	 * @return this instance <br>
	 */
	public MemberInfoBuilder setEnterprise(String enterprise) {
		if (type != Type.BUSINESS)
			return this;

		if (enterprise != null)
			this.enterprise = enterprise;
		else
			throw new IllegalArgumentException("Null enterprise string");
		return this;
	}

	/**
	 * Set birthday. <br>
	 * 
	 * @param birthday
	 *            birthdat LocalDate instance <br>
	 * @return this instance <br>
	 */
	public MemberInfoBuilder setBirthday(LocalDate birthday) {
		if (type != Type.PERSONAL)
			return this;

		if (birthday != null)
			this.birthday = birthday;
		else
			throw new IllegalArgumentException("Null birthday LocalDate");
		return this;
	}

	/**
	 * Check if builder can build a MembertInfo instance, that is, all fields
	 * are valid. <br>
	 * 
	 * @return if all fields are valid <br>
	 */
	public boolean isReady() {
		// System.out.println(id + " " + name + " " + contact + " " + type + " "
		// + enterprise + " " + birthday);
		return id != null && name != null && contact != null
				&& (type == Type.BUSINESS ? enterprise != null : birthday != null);
	}

	/**
	 * Build a MemberInfo instance. <br>
	 * If not ready,<b>IllgealStateException</b> will be thrown. <br>
	 * 
	 * @return MemberInfo instance built <br>
	 */
	public abstract MemberInfo getMemberInfo();
}
