package presentation.member.accessor;

import utils.info.member.MemberInfo;
import vo.member.MemberVo;

public interface InfoModifyAccessor {

	public MemberVo getModifiedMemberVo();

	public int getOldPasswordHash();

	public int getNewPasswordHash();

	public void setUser(MemberInfo info);

	public void setName(String name);

	public void setOldPassword(String password);

	public void setNewPassword(String password);

	public void setEmail(String email);

	public void setMobi(String mobi);

	public void setTele(String tele);

}
