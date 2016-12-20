package presentation.member.accessor.impl;

import static utils.exception.StaticExceptionFactory.accessorNotReadyEx;
import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import presentation.member.accessor.InfoModifyAccessor;
import utils.info.member.ContactInfo;
import utils.info.member.MemberInfo;
import vo.member.ContactVoBuilder;
import vo.member.MemberVo;
import vo.member.MemberVoBuilder;

/**
 * the content accessor of info modify of member info
 * 
 * @author paranoia
 *
 */
public final class InfoModifyAccessorImpl implements InfoModifyAccessor {

	private static InfoModifyAccessor instance;

	private String username;

	private String email;

	private String mobi;

	private String tele;

	private int oldPwdHash;

	private int newPwdHash;

	private MemberVo vo;

	public static final InfoModifyAccessor launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new InfoModifyAccessorImpl();
	}

	public static final InfoModifyAccessor getInstance() {
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}

	public static boolean isLaunched() {
		if (instance == null)
			return false;
		else
			return true;
	}

	@Override
	public MemberVo getModifiedMemberVo() {
		if (vo == null)
			throw accessorNotReadyEx();
		try {
			ContactInfo newContact = new ContactVoBuilder(vo.getContact()).setEmail(email).setMobilePhone(mobi)
					.setFixedPhone(tele).getContactInfo();
			MemberVoBuilder builder = new MemberVoBuilder(vo).setName(username).setContactInfo(newContact);
			vo = builder.getMemberInfo();

			return vo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void setName(String name) {
		this.username = name;
	}

	@Override
	public void setMobi(String mobi) {
		this.mobi = mobi;
	}

	@Override
	public void setTele(String tele) {
		this.tele = tele;
	}

	@Override
	public void setOldPassword(String password) {
		this.oldPwdHash = password.hashCode();
	}

	@Override
	public void setNewPassword(String password) {
		this.newPwdHash = password.hashCode();
	}

	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	@Override
	public void setUser(MemberInfo info) {
		this.vo = new MemberVoBuilder(info).getMemberInfo();
	}

	@Override
	public int getOldPasswordHash() {
		return oldPwdHash;
	}

	@Override
	public int getNewPasswordHash() {
		return newPwdHash;
	}

}
